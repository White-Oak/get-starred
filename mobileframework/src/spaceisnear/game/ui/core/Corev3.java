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
import spaceisnear.game.ui.*;
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
    @Setter private ScreenImprovedGreatly nextScreen;
    private Batch savedBatch;
    //
    //
    private Stage ownStage;
    private FlashMessage flashMessage;
    private ChatPanel chatPanel;

    @Override public void create() {
	Gdx.input.setInputProcessor(multiplexer);

	font = UIElement.font;
	viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	savedBatch = new SpriteBatch();
	camera = (OrthographicCamera) viewport.getCamera();
	camera.setToOrtho(true);
	savedBatch.setProjectionMatrix(camera.combined);
	ownStage = new Stage(viewport, savedBatch);
	multiplexer.addProcessor(ownStage);
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
	ScreenImprovedGreatly removePrevious = getScreenImprovedGreatly();
	screenImproved.initialize(this, savedBatch);
	super.setScreen(screenImproved);
	if (removePrevious != null) {
	    removeScreenImproved(removePrevious);
	}
	multiplexer.addProcessor(screenImproved.getStage());
    }

    public ScreenImprovedGreatly getScreenImprovedGreatly() {
	if (getScreen() != null) {
	    assert getScreen() instanceof ScreenImprovedGreatly;
	    return (ScreenImprovedGreatly) getScreen();
	}
	return null;
    }

    @Override
    public void render() {
	if (nextScreen != null) {
	    setScreenImproved(nextScreen);
	    nextScreen = null;
	}
	super.render();
	ownStage.draw();
	ownStage.act();
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

    public void addFlashMessage(FlashMessage flashMessage) {
	if (this.flashMessage != null) {
	    this.flashMessage.remove();

	}
	this.flashMessage = flashMessage;
	ownStage.addActor(flashMessage);
    }

    public ChatPanel createChatPanel() {
	if (chatPanel == null) {
	    chatPanel = new ChatPanel(ownStage);
	    ownStage.addActor(chatPanel);
	}
	return chatPanel;
    }
}
