package spaceisnear.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author White Oak
 */
public final class Button extends UIElement {

    private static final int WIDTH_PADDING = 20, HEIGHT_PADDING = 15;
    @Getter @Setter private String label;
    private Color color = new Color(0xdce0e1ff);

    public Button(String label) {
	this.label = label;
	initOvers();
    }

    @Override
    public void setColor(Color color) {
	this.color = color;
    }

    public void initOvers() {
	addListener(new ClickListener() {

	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		activated();
	    }

	});
    }

    @Override
    public void paint(Batch batch) {
	ShapeRenderer renderer = getRenderer();
//	renderer.begin(ShapeRenderer.ShapeType.Line);
//	renderer.setColor(Color.BLACK);
//	renderer.rect(0, 0, getWidth(), getHeight());
//	renderer.end();
	renderer.setColor(color);
	renderer.begin(ShapeRenderer.ShapeType.Filled);
	renderer.rect(0, 0, getWidth(), getHeight());
	renderer.end();
	batch.begin();
	font.setColor(Color.BLACK);
	font.draw(batch, label, getX() + WIDTH_PADDING, getY() + HEIGHT_PADDING + 2);
	batch.end();
    }

    @Override
    public float getPrefWidth() {
	//TODO
	GlyphLayout glyphLayout = new GlyphLayout(font, label);
	return (glyphLayout.width + WIDTH_PADDING * 2);
    }

    @Override
    public float getPrefHeight() {
	return (font.getLineHeight() + HEIGHT_PADDING * 2);
    }

}
