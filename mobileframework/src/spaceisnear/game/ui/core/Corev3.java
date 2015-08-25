package spaceisnear.game.ui.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.Setter;
import me.whiteoak.minlog.Log;
import spaceisnear.game.ui.UIElement;
import spaceisnear.starting.ui.ScreenImprovedGreatly;

/**
 * @author White Oak
 */
public class Corev3 extends com.badlogic.gdx.Game {

    public static BitmapFont font;
    private final InputMultiplexer multiplexer = new InputMultiplexer();
    @Getter private OrthographicCamera camera;
    @Getter private Viewport viewport;
    @Setter private ScreenImprovedGreatly startingScreen;
    private Batch savedBatch;

    @Override public void create() {
	Gdx.input.setInputProcessor(multiplexer);
	font = UIElement.font;
	viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	savedBatch = new SpriteBatch();
	camera = (OrthographicCamera) viewport.getCamera();
	camera.setToOrtho(true);
	savedBatch.setProjectionMatrix(camera.combined);
	if (startingScreen != null) {
	    setScreenImproved(startingScreen);
	}
	new Thread("Corev3") {
	    @Override
	    public void run() {
		update();
	    }
	}.start();
    }

    public void setScreenImproved(ScreenImprovedGreatly screenImproved) {
	ScreenImprovedGreatly removePrevious = null;
	if (getScreen() != null) {
	    assert getScreen() instanceof ScreenImprovedGreatly;
	    removePrevious = (ScreenImprovedGreatly) getScreen();
	}
	screenImproved.initialize(this, savedBatch);
	screenImproved.create();
	super.setScreen(screenImproved);
	if (removePrevious != null) {
	    removeScreenImproved(removePrevious);
	}
	multiplexer.addProcessor(screenImproved.getStage());
    }

    @Override
    @Deprecated
    public void setScreen(Screen screen) {
	throw new IllegalAccessError("You can't just add a screen! Use setScreenImproved");
    }

    private void removeScreenImproved(ScreenImprovedGreatly screenImproved) {
	Stage stage = screenImproved.getStage();
	multiplexer.removeProcessor(stage);
	screenImproved.dispose();
	stage.dispose();
    }

    private void update() {
	while (true) {
	    if (getScreen() != null) {
		((ScreenImprovedGreatly) getScreen()).update();
	    }
	    try {
		TimeUnit.MILLISECONDS.sleep(200L);
	    } catch (InterruptedException ex) {
		Log.error("SIN framework", "While updating Corev3", ex);
	    }
	}
    }

}
