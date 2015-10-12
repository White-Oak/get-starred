package me.oak.getstarred;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.network.Network;
import me.oak.getstarred.screens.NothingScreen;
import me.oak.getstarred.server.replies.Reply;
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
		    corev3.addFlashMessage(new FlashMessage(FlashMessage.Level.DEBUG, reply.toString()));
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
