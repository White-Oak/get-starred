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
import lombok.*;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class ChatPanel extends UIElement {

    @Setter private Color backgroundColor = new Color(0x303030af);
    private boolean hidden = true;
    @Getter private TextField textField;
    private TextView textView;

    private void resetHidden() {
	hidden = !hidden;
	if (hidden) {
	    addAction(Actions.sequence(Actions.touchable(Touchable.disabled),
		    Actions.moveBy(MAIN_WIDTH, 0, 0.2f),
		    Actions.touchable(Touchable.enabled)));
	} else {
	    addAction(Actions.sequence(Actions.touchable(Touchable.disabled),
		    Actions.moveBy(-MAIN_WIDTH, 0, 0.2f),
		    Actions.touchable(Touchable.enabled)));
	}
    }

    @Override
    public float getPrefWidth() {
	return EXTRA_WIDTH + MAIN_WIDTH;
    }

    @Override
    public float getPrefHeight() {
	return MAIN_HEIGHT;
    }

    public void add(CharSequence chatMessage) {
	textView.append("\n");
	textView.append(chatMessage);
    }

    @Override
    protected void init() {
	setX(Gdx.graphics.getWidth() - EXTRA_WIDTH);
	textField = new TextField();
	textView = new TextView();
	setBounds(getX(), 0, getWidth(), getHeight() - textField.getHeight());
	textView.setBounds(EXTRA_WIDTH, 0, MAIN_WIDTH, MAIN_HEIGHT);
	textField.setBounds(EXTRA_WIDTH, getHeight(),
		MAIN_WIDTH, textField.getHeight());
	addActor(textField);
	addActor(textView);
	textField.setActivationListener(actor -> activated());
	addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		System.out.println("HEY");
		resetHidden();
	    }

	});
    }
    private final static int EXTRA_WIDTH = Gdx.graphics.getWidth() / 20;
    private final static int EXTRA_HEIGHT = Gdx.graphics.getHeight() / 5;
    private final static int MAIN_WIDTH = Gdx.graphics.getWidth() / 3;
    private final static int MAIN_HEIGHT = Gdx.graphics.getHeight();
    private final static int EXTRA_Y = 20;

    @Override
    public void paint(Batch batch) {
	ShapeRenderer renderer = getRenderer();
	Gdx.gl.glEnable(GL20.GL_BLEND);
	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	renderer.begin(ShapeRenderer.ShapeType.Filled);
	renderer.setProjectionMatrix(batch.getProjectionMatrix());
	renderer.setColor(backgroundColor);
	renderer.rect(0, EXTRA_Y, EXTRA_WIDTH, EXTRA_HEIGHT);
	renderer.rect(EXTRA_WIDTH, 0, MAIN_WIDTH, MAIN_HEIGHT);
	renderer.end();
	Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    @Override
    protected void activated() {
	super.activated();
	textField.clear();
    }

}
