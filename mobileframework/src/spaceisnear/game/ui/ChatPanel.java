package spaceisnear.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
    private final Stage stage;

    private void resetHidden() {
	hidden = !hidden;
	resetPositions();
	if (hidden) {
	    textField.remove();
	} else {
	    stage.addActor(textField);
	    textField.setWidth(getWidth());
	    textField.setX(getX());
	    textField.setY(Gdx.graphics.getHeight() - textField.getHeight());
	}
    }

    public void resetPositions() {
	setX(getX());
	setY(getY());
	setWidth(getWidth());
	setHeight(getHeight());
    }

    @Override
    public float getWidth() {
	if (!hidden) {
	    return Gdx.graphics.getWidth() / 3;
	} else {
	    return Gdx.graphics.getWidth() / 20;
	}
    }

    @Override
    public float getX() {
	return Gdx.graphics.getWidth() - getWidth();
    }

    @Override
    public float getHeight() {
	if (!hidden) {
	    return Gdx.graphics.getHeight();
	} else {
	    return Gdx.graphics.getHeight() / 5;
	}
    }

    @Override
    public float getY() {
	if (!hidden) {
	    return 0;
	} else {
	    return 20;
	}
    }

    @Override
    protected void init() {
	resetPositions();
	textField = new TextField();
	textField.setActivationListener(actor -> activated());
	addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		System.out.println("HEY");
		resetHidden();
	    }

	});
    }

    @Override
    public void paint(Batch batch) {
	ShapeRenderer renderer = getRenderer();
	Gdx.gl.glEnable(GL20.GL_BLEND);
	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	renderer.begin(ShapeRenderer.ShapeType.Filled);
	renderer.setProjectionMatrix(batch.getProjectionMatrix());
	renderer.setColor(backgroundColor);
	renderer.rect(0, 0, getWidth(), getHeight());
	renderer.end();
	Gdx.gl.glDisable(GL20.GL_BLEND);
    }

}
