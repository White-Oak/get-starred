package me.oak.getstarred.screens;

import com.badlogic.gdx.graphics.Color;
import me.oak.getstarred.DebugActor;
import spaceisnear.game.ui.*;
import spaceisnear.starting.ui.ScreenImprovedGreatly;

/**
 *
 * @author White Oak
 */
public class RegistrationScreen extends ScreenImprovedGreatly implements ActivationListener {

    @Override
    public void create() {
	setBackgroundColor(new Color(0x95a5a6ff));
	getStage().addActor(new DebugActor());
	TextField loginTF = new TextField();
	loginTF.setPosition(getStage().getWidth() / 2 - (loginTF.getWidth()) / 2, getStage().getHeight() / 2);
	TextField passTF = new TextField();
	passTF.setPosition(getStage().getWidth() / 2 - (passTF.getWidth()) / 2,
		getStage().getHeight() / 2 + loginTF.getHeight() + 15);
	Button reg = new Button("Register");
	reg.setPosition(getStage().getWidth() / 2 - (reg.getWidth()) / 2,
		getStage().getHeight() / 2 + +loginTF.getHeight() + passTF.getHeight() + 30);
	getStage().addActor(reg);
	getStage().addActor(loginTF);
	getStage().addActor(passTF);
    }

    @Override
    public void componentActivated(UIElement actor) {
	throw new RuntimeException("Method componentActivated() in class RegistrationScreen is not implemented yet!");
    }

}
