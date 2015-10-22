package spaceisnear.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import lombok.*;

/**
 *
 * @author White Oak
 */
public abstract class UIElement extends Group {

    public static final BitmapFont font;
    @Getter(AccessLevel.PROTECTED) private final static ShapeRenderer renderer = new ShapeRenderer();

    @Getter @Setter private ActivationListener activationListener;

    public static int determineFontSize() {
	final int width = Gdx.graphics.getWidth();
	if (width >= 1920) {
	    return 50;
	} else if (width <= 1920 && width > 1280) {
	    return 48;
	} else if (width <= 1280 && width > 1200) {
	    return 24;
	} else {
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
	init();
    }

    protected void init() {
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
	renderer.translate(getX(), getY(), 0);
	batch.end();
	paint(batch);
	batch.begin();
	super.draw(batch, parentAlpha);
	renderer.translate(-getX(), -getY(), 0);
    }

    public abstract void paint(Batch batch);

    protected int getLineWidth(String label) {
	GlyphLayout glyphLayout = new GlyphLayout(font, label);
	return (int) glyphLayout.width;
    }
}
