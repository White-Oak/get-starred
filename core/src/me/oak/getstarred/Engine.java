package me.oak.getstarred;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.network.Network;
import me.oak.getstarred.network.messages.FindMessage;
import me.oak.getstarred.screens.MainMenuScreen;
import me.oak.getstarred.screens.NothingScreen;
import me.oak.getstarred.server.replies.*;
import me.whiteoak.minlog.Log;
import spaceisnear.game.ui.FlashMessage;
import spaceisnear.game.ui.core.Corev3;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class Engine {

    private final Network network;
    private final Corev3 corev3;
    private LoginReply loginReply;

    private boolean findingMatch;
    private long lastTimeAskedToFound;
    private final static long TIME_BETWEEN_FINDS = 2000l;

    public void start() {
	Thread thread = new Thread(this::proccessNetwork, "network");
	thread.start();
    }

    private void proccessNetwork() {
	while (true) {
	    network.sendQueued();
	    Collection<Reply> replies = network.processReceived();
	    if (replies != null) {
		for (Reply reply : replies) {
		    switch (reply.getType()) {
			case REGISTER:
			    flashOfStatusable((Statusable) reply);
			    final NothingScreen nothingScreen = new NothingScreen(network);
			    corev3.setNextScreen(nothingScreen);
			    break;
			case LOGIN:
			    flashOfStatusable((Statusable) reply);
			    loginReply = (LoginReply) reply;
			    network.setDigest(loginReply.getDigest());
			    final MainMenuScreen mainMenuScreen = new MainMenuScreen(network);
			    corev3.setNextScreen(mainMenuScreen);
			    break;
			case FINDING:
			    final Statusable name = (Statusable) reply;
			    flashOfStatusable(name);
			    findingMatch = name.getStatus() == Statusable.Status.ERROR;
			    break;
		    }
		}
	    }
	    if (findingMatch) {
		if (System.currentTimeMillis() - lastTimeAskedToFound > TIME_BETWEEN_FINDS) {
		    FindMessage findMessage = new FindMessage(loginReply.getDigest());
		    network.queue(findMessage);
		    lastTimeAskedToFound = System.currentTimeMillis();
		}
	    }
	    try {
		Thread.sleep(200L);
	    } catch (InterruptedException ex) {
		Log.error("client", "While iterating through casual engine update cycle", ex);
	    }
	}
    }

    private void flashOfStatusable(Statusable statusable) {
	FlashMessage.Level level = transmute(statusable.getStatus());
	final FlashMessage flashMessage = new FlashMessage(level, statusable.toString());
	corev3.addFlashMessage(flashMessage);
    }

    private FlashMessage.Level transmute(Statusable.Status status) {
	switch (status) {
	    case DEBUG:
		return FlashMessage.Level.DEBUG;
	    case INFO:
		return FlashMessage.Level.INFO;
	    case ERROR:
		return FlashMessage.Level.ERROR;
	    case SUCCESS:
		return FlashMessage.Level.SUCCESS;
	    case WARNING:
		return FlashMessage.Level.WARNING;
	    default:
		throw new AssertionError("wut");
	}
    }
}
