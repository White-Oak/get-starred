package me.oak.getstarred;

import me.oak.getstarred.network.ClientNetwork;
import me.oak.getstarred.network.Network;
import me.oak.getstarred.screens.NothingScreen;
import me.oak.getstarred.server.spring.replies.*;
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
	RegisterReply register = clientNetwork.register("Oak", "1234");
	System.out.println(register);
	LoginReply login = clientNetwork.login("Oak", "1234");
	System.out.println(login);
	Reply logout = clientNetwork.logout(login.getDigest());
	System.out.println(logout);
    }
}
