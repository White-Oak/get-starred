package spaceisnear.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import lombok.*;

/**
 *
 * @author White Oak
 */
public abstract class UIElement extends Actor {

    public static final BitmapFont font;
    @Getter(AccessLevel.PROTECTED) private final static ShapeRenderer renderer = new ShapeRenderer();
    private final OrthographicCamera camera = new OrthographicCamera(1200, 600);
    private boolean hovered;

    @Getter @Setter private ActivationListener activationListener;

    public static int determineFontSize() {
	switch (Gdx.graphics.getWidth()) {
	    case 1920:
		return 36;
	    case 1280:
		return 24;
	    default:
		return 20;
	}
    }

    static {
	FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Segoe_UI.ttf"));
	FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	parameter.size = determineFontSize();
	parameter.flip = true;

	font = generator.generateFont(parameter);
	generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    public UIElement() {
	camera.setToOrtho(true);
	camera.update();
	renderer.setProjectionMatrix(camera.combined);
	addListener(new ClickListener() {

	    @Override
	    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		hovered = true;
	    }

	    @Override
	    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
		hovered = false;
	    }

	});
    }

    protected void activated() {
	if (activationListener != null) {
	    activationListener.componentActivated(this);
	}
    }

    public float getPrefHeight() {
	return 0;
    }

    public float getPrefWidth() {
	return 0;
    }

    @Override
    public float getWidth() {
	if (super.getWidth() == 0) {
	    setWidth(getPrefWidth());
	}
	return super.getWidth();
    }

    @Override
    public float getHeight() {
	if (super.getHeight() == 0) {
	    setHeight(getPrefHeight());
	}
	return super.getHeight();
    }

    @Override
    public final void draw(Batch batch, float parentAlpha) {
	batch.end();
	renderer.translate(getX(), getY(), 0);
	paint(batch);
	renderer.translate(-getX(), -getY(), 0);
	batch.begin();
    }

    public abstract void paint(Batch batch);

}
