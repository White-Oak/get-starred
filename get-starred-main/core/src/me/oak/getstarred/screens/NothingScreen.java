package me.oak.getstarred.screens;

import com.badlogic.gdx.graphics.Color;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.DebugActor;
import me.oak.getstarred.network.Network;
import spaceisnear.game.ui.Button;
import spaceisnear.starting.ui.ScreenImprovedGreatly;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class NothingScreen extends ScreenImprovedGreatly {

    private final Network network;

    @Override
    public void create() {
	setBackgroundColor(new Color(0x95a5a6ff));
	getStage().addActor(new DebugActor());
	Button reg = new Button("Registration");
	Button log = new Button("Login");
	reg.setPosition(getStage().getWidth() / 2 - (log.getWidth() + reg.getWidth() + 10) / 2,
		getStage().getHeight() / 2 - reg.getHeight() / 2);
	log.setPosition(reg.getX() + reg.getWidth() + 10, reg.getY());
	getStage().addActor(log);
	getStage().addActor(reg);
	reg.setActivationListener(actor -> setScreen(new RegistrationScreen(network)));
	log.setActivationListener(actor -> setScreen(new LoginScreen(network)));
    }

}
