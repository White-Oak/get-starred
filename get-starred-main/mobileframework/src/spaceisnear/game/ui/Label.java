package spaceisnear.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.RequiredArgsConstructor;
import static spaceisnear.game.ui.UIElement.font;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class Label extends UIElement {

    private final String text;

    @Override
    public float getPrefWidth() {
	return getLineWidth(text);
    }

    @Override
    public float getPrefHeight() {
	return font.getLineHeight();
    }

    @Override
    public void paint(Batch batch) {
	batch.begin();
	font.setColor(Color.BLACK);
	font.draw(batch, text, getX(), getY());
	batch.end();
    }

}
