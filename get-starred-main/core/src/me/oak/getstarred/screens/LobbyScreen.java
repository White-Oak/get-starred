package me.oak.getstarred.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.DebugActor;
import me.oak.getstarred.network.Network;
import me.oak.getstarred.network.User;
import me.oak.getstarred.network.messages.ReadyMessage;
import spaceisnear.game.ui.Button;
import spaceisnear.game.ui.Label;
import spaceisnear.starting.ui.ScreenImprovedGreatly;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class LobbyScreen extends ScreenImprovedGreatly {

    private final User me, him;
    private final Network network;

    @Override
    public void create() {
	setBackgroundColor(new Color(0x95a5a6ff));
	stage.addActor(new DebugActor());
	int halfwidth = Gdx.graphics.getWidth() / 2;
	int quarterwidth = Gdx.graphics.getWidth() / 4;
	Label label = new Label("User");
	label.setPosition(quarterwidth - label.getWidth() / 2, 20);
	Label labelId = new Label("Name: " + me.getLogin());
	labelId.setPosition(quarterwidth - labelId.getWidth() / 2, 120);
	Label label2 = new Label("Other User");
	label2.setPosition(halfwidth + quarterwidth - label2.getWidth() / 2, 20);
	Label label2Id = new Label("Name: " + him.getLogin());
	label2Id.setPosition(halfwidth + quarterwidth - label2Id.getWidth() / 2, 120);
	stage.addActor(label);
	stage.addActor(label2);
	stage.addActor(labelId);
	stage.addActor(label2Id);

	Button ready = new Button("Ready");
	ready.setPosition(halfwidth - ready.getWidth() / 2, Gdx.graphics.getHeight() - ready.getHeight() - 20);
	ready.setActivationListener(actor -> {
	    network.queue(new ReadyMessage(network.getDigest()));
	    ready.remove();
	    Label labelRdy = new Label("Waiting...");
	    labelRdy.setPosition(halfwidth - labelRdy.getWidth() / 2, ready.getY());
	    getStage().addActor(labelRdy);
	});
	stage.addActor(ready);
    }

}
