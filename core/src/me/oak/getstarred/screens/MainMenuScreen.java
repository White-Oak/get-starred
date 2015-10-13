package me.oak.getstarred.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import me.oak.getstarred.DebugActor;
import spaceisnear.game.ui.Button;
import spaceisnear.starting.ui.ScreenImprovedGreatly;

/**
 *
 * @author White Oak
 */
public class MainMenuScreen extends ScreenImprovedGreatly {

    @Override
    public void create() {
	setBackgroundColor(new Color(0x95a5a6ff));
	getStage().addActor(new DebugActor());
	Button reg = new Button("Find a game");
	Button log = new Button("Quit a game");
	reg.setPosition(getStage().getWidth() / 2 - (log.getWidth() + reg.getWidth() + 10) / 2,
		getStage().getHeight() / 2 - reg.getHeight() / 2);
	log.setPosition(reg.getX() + reg.getWidth() + 10, reg.getY());
	getStage().addActor(log);
	getStage().addActor(reg);
	reg.setActivationListener(null);
	log.setActivationListener(actor -> Gdx.app.exit());
    }
}
