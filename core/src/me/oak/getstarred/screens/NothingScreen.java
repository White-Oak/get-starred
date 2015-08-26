package me.oak.getstarred.screens;

import com.badlogic.gdx.graphics.Color;
import me.oak.getstarred.DebugActor;
import spaceisnear.game.ui.*;
import spaceisnear.starting.ui.ScreenImprovedGreatly;

/**
 *
 * @author White Oak
 */
public class NothingScreen extends ScreenImprovedGreatly implements ActivationListener {

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
	reg.setActivationListener(this);
	log.setActivationListener(this);
    }

    @Override
    public void componentActivated(UIElement actor) {
	Button b = (Button) actor;
	switch (b.getLabel()) {
	    case "Registration":
		setScreen(new RegistrationScreen());
		break;
	    case "Login":
		setScreen(new LoginScreen());
		break;
	}
    }

}
