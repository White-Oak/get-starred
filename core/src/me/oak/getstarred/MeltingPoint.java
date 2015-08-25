package me.oak.getstarred;

import me.oak.getstarred.network.Network;
import me.oak.getstarred.network.messages.RegistrationMessage;
import me.oak.getstarred.screens.NothingScreen;
import spaceisnear.game.ui.core.Corev3;

/**
 *
 * @author White Oak
 */
public class MeltingPoint extends Corev3 {

    private final Network network = new Network(new ClientContext());

    public MeltingPoint() {
	setStartingScreen(new NothingScreen());
	network.connect("localhost");
	network.send(new RegistrationMessage("Oka", "sihkxgk"));
	network.sendQueued();
	while (true) {
	    network.processReceived();
	}
    }

}
