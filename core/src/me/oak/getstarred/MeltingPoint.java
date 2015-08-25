package me.oak.getstarred;

import com.sun.jersey.api.client.ClientResponse;
import me.oak.getstarred.network.ClientNetwork;
import me.oak.getstarred.network.Network;
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
	ClientNetwork clientNetwork = new ClientNetwork();
	ClientResponse register = clientNetwork.register("Oak", "1234");
	System.out.println("Status: " + register.getStatus());
	System.out.println("Response: " + register.getEntity(String.class));
	register = clientNetwork.login("Oak", "1234");
	System.out.println("Status: " + register.getStatus());
	System.out.println("Response: " + register.getEntity(String.class));
    }

}
