package me.oak.getstarred.screens;

import com.badlogic.gdx.graphics.Color;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.DebugActor;
import me.oak.getstarred.network.Network;
import me.oak.getstarred.network.messages.RegistrationMessage;
import spaceisnear.game.ui.*;
import spaceisnear.starting.ui.ScreenImprovedGreatly;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class RegistrationScreen extends ScreenImprovedGreatly implements ActivationListener {

    private TextField loginTF, passTF;
    private final Network network;

    @Override
    public void create() {
	setBackgroundColor(new Color(0x95a5a6ff));
	getStage().addActor(new DebugActor());
	loginTF = new TextField();
	loginTF.setText("Oak");
	final float halfWidth = getStage().getWidth() / 2;
	final float halfHeight = getStage().getHeight() / 2;
	loginTF.setPosition(halfWidth - loginTF.getWidth() / 2, halfHeight);
	passTF = new TextField();
	passTF.setText("1234");
	passTF.setPosition(halfWidth - passTF.getWidth() / 2, halfHeight + loginTF.getHeight() + 15);
	Button regButton = new Button("Register");
	regButton.setPosition(halfWidth - regButton.getWidth() / 2, halfHeight + loginTF.getHeight() + passTF.getHeight() + 30);
	regButton.setActivationListener(this);
	getStage().addActor(regButton);
	getStage().addActor(loginTF);
	getStage().addActor(passTF);
    }

    @Override
    public void componentActivated(UIElement actor) {
	System.out.println("Register");
	network.queue(new RegistrationMessage(loginTF.getText(), passTF.getText()));
    }

}
