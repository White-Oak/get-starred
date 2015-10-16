package me.oak.getstarred.screens;

import com.badlogic.gdx.graphics.Color;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.DebugActor;
import me.oak.getstarred.network.Network;
import me.oak.getstarred.network.messages.LoginMessage;
import spaceisnear.game.ui.*;
import spaceisnear.starting.ui.ScreenImprovedGreatly;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class LoginScreen extends ScreenImprovedGreatly implements ActivationListener {

    private final Network network;
    private TextField loginTF, passTF;

    @Override
    public void create() {
	setBackgroundColor(new Color(0x95a5a6ff));
	getStage().addActor(new DebugActor());
	loginTF = new TextField("Oak");
	final float halfWidth = getStage().getWidth() / 2;
	final float halfHeight = getStage().getHeight() / 2;
	loginTF.setPosition(halfWidth - loginTF.getWidth() / 2, halfHeight);
	passTF = new TextField("1234");
	passTF.setPosition(halfWidth - passTF.getWidth() / 2, halfHeight + loginTF.getHeight() + 15);
	Button regButton = new Button("Login");
	regButton.setPosition(halfWidth - regButton.getWidth() / 2, halfHeight + +loginTF.getHeight() + passTF.getHeight() + 30);
	regButton.setActivationListener(this);
	getStage().addActor(regButton);
	getStage().addActor(loginTF);
	getStage().addActor(passTF);
    }

    @Override
    public void componentActivated(UIElement actor) {
	network.queue(new LoginMessage(loginTF.getText(), passTF.getText()));
    }

}
