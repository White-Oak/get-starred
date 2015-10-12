package me.oak.getstarred;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.network.Network;
import me.oak.getstarred.screens.NothingScreen;
import me.oak.getstarred.server.replies.Reply;
import me.oak.getstarred.server.replies.Statusable;
import spaceisnear.game.ui.FlashMessage;
import spaceisnear.game.ui.core.Corev3;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class Engine {

    private final Network network;
    private final Corev3 corev3;

    public void start() {
	Thread thread = new Thread(new Runnable() {
	    @Override
	    public void run() {
		proccessNetwork();
	    }
	}, "network");
	thread.start();
    }

    private void proccessNetwork() {
	while (true) {
	    network.sendQueued();
	    Collection<Reply> replies = network.processReceived();
	    if (replies != null) {
		for (Reply reply : replies) {
		    final NothingScreen nothingScreen = new NothingScreen(network);
		    FlashMessage.Level level = FlashMessage.Level.DEBUG;
		    if (reply instanceof Statusable) {
			Statusable statusable = (Statusable) reply;
			level = statusable.getStatus().equals("success") ? FlashMessage.Level.SUCCESS : FlashMessage.Level.ERROR;
		    }
		    final FlashMessage flashMessage = new FlashMessage(level, reply.toString());
		    corev3.addFlashMessage(flashMessage);
		    corev3.setNextScreen(nothingScreen);
		}
	    }
	    try {
		Thread.sleep(200L);
	    } catch (InterruptedException ex) {
		ex.printStackTrace();
	    }
	}
    }
}
