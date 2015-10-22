package me.oak.getstarred.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.DebugActor;
import me.oak.getstarred.network.Network;
import me.oak.getstarred.network.messages.FindMessage;
import spaceisnear.game.ui.Button;
import spaceisnear.game.ui.Label;
import spaceisnear.starting.ui.ScreenImprovedGreatly;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class MainMenuScreen extends ScreenImprovedGreatly {

    private final Network network;

    @Override
    public void create() {
	setBackgroundColor(new Color(0x95a5a6ff));
	getStage().addActor(new DebugActor());
	final Button find = new Button("Find a game");
	final Button quit = new Button("Quit a game");
	final float halfWidth = getStage().getWidth() / 2;
	final float halfHeight = getStage().getHeight() / 2;
	find.setPosition(halfWidth - (find.getWidth()) / 2, halfHeight - 50);
	quit.setPosition(halfWidth - (quit.getWidth()) / 2, halfHeight + 50);
	getStage().addActor(quit);
	getStage().addActor(find);
	find.setActivationListener(actor -> {
	    network.queue(new FindMessage(network.getDigest()));
	    find.remove();
	    Label label = new Label("Looking for a sign...");
	    label.setPosition(halfWidth - label.getWidth() / 2, halfHeight - 30);
	    getStage().addActor(label);
	});
	quit.setActivationListener(actor -> Gdx.app.exit());
    }
}
