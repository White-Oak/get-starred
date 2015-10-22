package me.oak.getstarred;

import me.oak.getstarred.network.Network;
import me.oak.getstarred.screens.NothingScreen;
import spaceisnear.game.ui.core.Corev3;

/**
 *
 * @author White Oak
 */
public class MeltingPoint extends Corev3 {

    private final Network network = new Network(new ClientContext());
    private final Engine engine;

    public MeltingPoint() {
	network.start();
	setStartingScreen(new NothingScreen(network));
	engine = new Engine(network, this);
	engine.start();
    }

}
