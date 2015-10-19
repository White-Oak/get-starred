package spaceisnear.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author White Oak
 */
public class SidePanel extends UIElement {

    protected final static int EXTRA_WIDTH = Gdx.graphics.getWidth() / 20;
    protected final static int EXTRA_HEIGHT = Gdx.graphics.getHeight() / 5;
    protected final static int MAIN_WIDTH = Gdx.graphics.getWidth() / 3;
    protected final static int MAIN_HEIGHT = Gdx.graphics.getHeight();

    @Setter private Color backgroundColor = new Color(0x303030af);
    @Getter private boolean hidden = true;
    private final TextView textView;

    private int extraY;

    public SidePanel() {
	setX(Gdx.graphics.getWidth() - EXTRA_WIDTH);
	textView = new TextView();
	setBounds(getX(), 0, getWidth(), getHeight());
	textView.setBounds(EXTRA_WIDTH, 0, MAIN_WIDTH, MAIN_HEIGHT);
	addActor(textView);
	final UIElement mouseCatcher = new UIElement() {
	    {
		{
		    addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
			    System.out.println("HEY");
			    resetHidden();
			}

		    });
		}
	    }

	    @Override
	    public void paint(Batch batch) {
	    }
	};
	mouseCatcher.setBounds(0, extraY, EXTRA_WIDTH, EXTRA_HEIGHT);
	addActor(mouseCatcher);
    }

    protected void resetHidden() {
	hidden = !hidden;
	if (hidden) {
	    addAction(Actions.sequence(Actions.touchable(Touchable.disabled),
		    Actions.moveBy(MAIN_WIDTH, 0, 0.2f),
		    Actions.touchable(Touchable.enabled)));
	    textView.addAction(Actions.sequence(Actions.touchable(Touchable.disabled),
		    Actions.delay(0.2f),
		    Actions.visible(false)));
	} else {
	    addAction(Actions.sequence(Actions.touchable(Touchable.disabled),
		    Actions.moveBy(-MAIN_WIDTH, 0, 0.2f),
		    Actions.touchable(Touchable.enabled)));
	    textView.addAction(Actions.sequence(Actions.visible(true),
		    Actions.delay(0.2f),
		    Actions.touchable(Touchable.enabled)));
	}
    }

    @Override
    public final float getPrefWidth() {
	return EXTRA_WIDTH + MAIN_WIDTH;
    }

    @Override
    public final float getPrefHeight() {
	return MAIN_HEIGHT;
    }

    public final void add(CharSequence chatMessage) {
	textView.append("\n");
	textView.append(chatMessage);
    }

    @Override
    public final void paint(Batch batch) {
	ShapeRenderer renderer = getRenderer();
	Gdx.gl.glEnable(GL20.GL_BLEND);
	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	renderer.begin(ShapeRenderer.ShapeType.Filled);
	renderer.setProjectionMatrix(batch.getProjectionMatrix());
	renderer.setColor(backgroundColor);
	renderer.rect(0, extraY, EXTRA_WIDTH, EXTRA_HEIGHT);
	renderer.rect(EXTRA_WIDTH, 0, MAIN_WIDTH, MAIN_HEIGHT);
	renderer.end();
	Gdx.gl.glDisable(GL20.GL_BLEND);
    }

}
