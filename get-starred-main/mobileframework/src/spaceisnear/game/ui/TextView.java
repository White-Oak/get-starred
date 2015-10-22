package spaceisnear.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Align;
import static spaceisnear.game.ui.UIElement.font;

/**
 *
 * @author White Oak
 */
public class TextView extends UIElement {

    private final StringBuilder sb = new StringBuilder();

    public void append(CharSequence charSequence) {
	sb.append(charSequence);
    }

    public void set(CharSequence charSequence) {
	sb.delete(0, sb.length());
	append(charSequence);
    }

    @Override
    protected void init() {
	setTouchable(Touchable.disabled);
    }

    @Override
    public void paint(Batch batch) {

	batch.begin();
	font.setColor(Color.BLACK);
	font.draw(batch, sb, getX() + 10, getY() + 10, getWidth() - 20, Align.left, true);
	batch.end();
    }

}
