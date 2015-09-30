package me.oak.getstarred;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.network.Network;
import me.oak.getstarred.screens.NothingScreen;
import me.oak.getstarred.server.replies.*;
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
		    if (reply instanceof RegisterReply) {
			corev3.setNextScreen(new NothingScreen(network));
		    } else if (reply instanceof LoginReply) {
			corev3.setNextScreen(new NothingScreen(network));
		    }
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
