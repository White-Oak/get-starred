package spaceisnear.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.*;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class OverflowTextField extends UIElement implements ActivationListener {

    @Setter private Color backgroundColor = new Color(0xf0f0f19f);
    private final TextField textField;
    private final com.badlogic.gdx.scenes.scene2d.ui.TextField.OnscreenKeyboard keyboard;
    private Button regButton;

    @Override
    public void init() {
	addListener(new InputListener() {
	    @Override
	    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		keyboard.show(true);
		return true;
	    }

	});
    }

    public void addToStage(Stage stage) {
	regButton = new Button("OK");
	final float halfWidth = getWidth() / 2;
	final float halfHeight = getHeight() / 2;
	regButton.setPosition(halfWidth - regButton.getWidth() / 2, halfHeight);
	regButton.setActivationListener(this);
	stage.addActor(regButton);
    }

    @Override
    public float getPrefWidth() {
	return Gdx.graphics.getWidth();
    }

    @Override
    public float getPrefHeight() {
	return Gdx.graphics.getHeight();
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
	batch.begin();
	font.setColor(Color.BLACK);
	font.draw(batch, textField.getText(), getX() + 20, getY() + 20);
	batch.end();
    }

    @Override
    protected void activated() {
	super.activated();
	regButton.remove();
	remove();
    }

    @Override
    public void componentActivated(UIElement actor) {
	textField.activated();
    }

}
