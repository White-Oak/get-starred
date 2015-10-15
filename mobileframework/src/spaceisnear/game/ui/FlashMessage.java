package spaceisnear.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author White Oak
 */
@AllArgsConstructor public class FlashMessage extends UIElement {

    private final static int Y_STARTING = 20;
    private final static int HEIGHT_PADDING = 10;

    private final Level level;
    private final String title;
    private final String text;

    public FlashMessage(Level level, String text) {
	this(level, level.defaultLabel, text);
    }

    @Override
    protected void init() {
	setX((Gdx.graphics.getWidth() - getWidth()) / 2);
	setY(-getHeight());
	MoveToAction moveByAction = new MoveToAction();
	moveByAction.setPosition(getX(), Y_STARTING);
	moveByAction.setDuration(0.5f);
	addAction(moveByAction);
	DelayAction delayAction = new DelayAction(3);
	MoveToAction moveByAction2 = new MoveToAction();
	moveByAction2.setPosition(getX(), -getHeight());
	moveByAction2.setDuration(0.5f);
	SequenceAction sequenceAction = new SequenceAction(moveByAction, delayAction, moveByAction2);
	addAction(sequenceAction);
    }

    @Override
    public void paint(Batch batch) {
	ShapeRenderer renderer = getRenderer();
	Gdx.gl.glEnable(GL20.GL_BLEND);
	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	renderer.begin(ShapeRenderer.ShapeType.Filled);
	renderer.setProjectionMatrix(batch.getProjectionMatrix());
	renderer.setColor(level.color);
	renderer.rect(0, 0, getWidth(), getHeight());
	renderer.end();
	Gdx.gl.glDisable(GL20.GL_BLEND);
	batch.begin();
	font.setColor(Color.BLACK);
	font.draw(batch, title, getWidth() / 2 + getX() - getLineWidth(title) / 2, getY() + HEIGHT_PADDING);
	font.draw(batch, text, getWidth() / 2 + getX() - getLineWidth(text) / 2, getY() + HEIGHT_PADDING * 2 + font.getLineHeight());
	batch.end();
    }

    @Override
    public float getPrefWidth() {
	return Gdx.graphics.getWidth() * 0.8f;
    }

    @Override
    public float getPrefHeight() {
	return font.getLineHeight() * 2 + HEIGHT_PADDING * 3;
    }

    @RequiredArgsConstructor public static enum Level {
	DEBUG(new Color(0xafafafaf), "Debug"), INFO(new Color(0xafafffaf), "Info"),
	SUCCESS(new Color(0xafffafaf), "Success"), WARNING(new Color(0x9fffffaf), "Warning"),
	ERROR(new Color(0xff8f8faf), "Failure");
	private final Color color;
	private final String defaultLabel;
    }
}
