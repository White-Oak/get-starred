package spaceisnear.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import lombok.*;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public final class Button extends UIElement {

    private static final int WIDTH_PADDING = 20, HEIGHT_PADDING = 15;
    @Getter private final String label;
    @Setter private Color color = new Color(0xdce0e1ff);

    @Override
    protected void init() {
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
	return (getLineWidth(label) + WIDTH_PADDING * 2);
    }

    @Override
    public float getPrefHeight() {
	return (font.getLineHeight() + HEIGHT_PADDING * 2);
    }

}
