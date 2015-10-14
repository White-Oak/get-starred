/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceisnear.game.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.*;
import lombok.Getter;
import lombok.Setter;
import static spaceisnear.game.ui.UIElement.font;

/**
 *
 * @author White Oak
 */
public final class TextField extends UIElement {

    private static final int HEIGHT_PADDING = 2;
    private static final int WIDTH_PADDING = 10;
    private final static long DELTA_ACTED = 100L;

    private StringBuilder text = new StringBuilder();
    private int currentPosition;
    private Color textColor = new Color(0x0);
    @Getter private boolean focused;
    private int keycode;
    private long lastTimeActed;
    @Setter private Color borderColor = new Color(0x0);
    private final Color backgroundColor = new Color(0xf7f7ffdc);
    private final com.badlogic.gdx.scenes.scene2d.ui.TextField.OnscreenKeyboard keyboard = new com.badlogic.gdx.scenes.scene2d.ui.TextField.DefaultOnscreenKeyboard();
//
    private OverflowTextField currentOverflow;

    public TextField() {
	this("");
    }

    public TextField(CharSequence text) {
	this.text.append(text);
    }

    @Override
    protected void init() {
	addListener(new InputListener() {

	    @Override
	    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		if (currentOverflow == null) {
		    Stage stage = getStage();
		    if (stage != null) {
			stage.setKeyboardFocus(TextField.this);
//			final Application.ApplicationType type = Gdx.app.getType();
			final Application.ApplicationType type = Application.ApplicationType.Android;
			switch (type) {
			    case Desktop:
				int currentX = WIDTH_PADDING;
				currentPosition = 0;
				while (currentX < x && currentPosition < text.length()) {
				    currentPosition++;
				    //TODO
				    GlyphLayout glyphLayout = new GlyphLayout(font, text.subSequence(0, currentPosition));
				    currentX += glyphLayout.width;
				}
				break;
			    case Android:
				currentPosition = text.length();
				currentOverflow = new OverflowTextField(TextField.this, keyboard);
				stage.addActor(currentOverflow);
				currentOverflow.addToStage(stage);
				keyboard.show(true);
				break;
			}
		    }
		}
		return true;
	    }

	    @Override
	    public void touchDragged(InputEvent event, float x, float y, int pointer) {
//		super.touchDragged(event, x, y, pointer);
//		lastBlink = 0;
//		cursorOn = false;
//		setCursorPosition(x);
//		hasSelection = true;
	    }

	    @Override
	    public boolean keyDown(InputEvent event, int keycode) {
		TextField.this.keycode = keycode;
		lastTimeActed = 0;
		return true;
	    }

	    @Override
	    public boolean keyTyped(InputEvent event, char character) {
//		System.out.println(character);
		if (character == '\u0008') {
		    TextField.this.keycode = Input.Keys.BACKSPACE;
		    lastTimeActed = 0;
		} else if (character == '\n' || character == '\r') {
		    TextField.this.keycode = Input.Keys.ENTER;
		    lastTimeActed = 0;
		} else if (!Character.isIdentifierIgnorable(character)) {
		    addCharacter(character);
		}
		return true;
	    }

	    @Override
	    public boolean keyUp(InputEvent event, int keycode) {
		TextField.this.keycode = 0;
		return true;
	    }
	});
    }

    public boolean checkKeys(int keycode) {
	if (keycode != 0) {
	    if (System.currentTimeMillis() - lastTimeActed > DELTA_ACTED) {
		switch (keycode) {
		    case Input.Keys.ENTER:
			activated();
			break;
		    case Input.Keys.BACKSPACE:
			removeLastCharacter();
			break;
		    case Input.Keys.LEFT:
			currentPosition--;
			if (currentPosition < 0) {
			    currentPosition = 0;
			}
			break;
		    case Input.Keys.RIGHT:
			currentPosition++;
			if (currentPosition > text.length()) {
			    currentPosition = text.length();
			}
			break;
		    default:
			return false;
		}
		lastTimeActed = System.currentTimeMillis();
	    }
	    return true;
	}
	return false;
    }

    @Override
    public float getPrefHeight() {
	return font.getLineHeight() + HEIGHT_PADDING * 2;
    }

    @Override
    public float getPrefWidth() {
	return 200;
    }

    public void addCharacter(char c) {
	if (currentPosition >= text.length()) {
	    text.append(c);
	} else {
	    text.insert(currentPosition, c);
	}
	currentPosition++;
    }

    public void removeLastCharacter() {
	if (currentPosition > 0) {
	    text.deleteCharAt(--currentPosition);
	}
    }

    @Override
    public void paint(Batch batch) {
	if (checkKeys(keycode)) {
	    keycode = 0;
	}
	ShapeRenderer renderer = getRenderer();
	focused = getStage().getKeyboardFocus() == this;
	int start = 0;
	int end = text.length();
	int startingXText = 0;
	//TODO
	GlyphLayout glyphLayout = new GlyphLayout(font, text);
	if (glyphLayout.width > getWidthWithoutPaddings()) {
	    end = text.length();
	    start = end - 1;
	    glyphLayout.setText(font, text, start, end, textColor, getWidthWithoutPaddings(), 0, false, null);
	    while (start > 0 && glyphLayout.width < getWidthWithoutPaddings()) {
		start--;
	    }
	    glyphLayout.setText(font, text, 0, start, textColor, getWidthWithoutPaddings(), 0, false, null);
	    startingXText = (int) -glyphLayout.width;
	}
	//
	{
	    Gdx.gl.glEnable(GL20.GL_BLEND);
	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	    renderer.setProjectionMatrix(batch.getProjectionMatrix());
	    renderer.begin(ShapeRenderer.ShapeType.Filled);
	    renderer.setColor(backgroundColor);
	    renderer.rect(0, 0, getWidth(), getHeight());
	    renderer.end();
	    Gdx.gl.glDisable(GL20.GL_BLEND);
	}
	{
	    renderer.begin(ShapeRenderer.ShapeType.Line);
	    renderer.setColor(borderColor);
	    renderer.rect(0, 0, getWidth() + 1, getHeight());
	    renderer.end();
	}
	//cursor
	renderer.begin(ShapeRenderer.ShapeType.Line);
	font.setColor(Color.BLACK);
	if (focused) {
	    glyphLayout.setText(font, text, 0, currentPosition, textColor, getWidthWithoutPaddings(), 0, false, null);
	    final float x = 10 + glyphLayout.width + startingXText;
	    renderer.line(x, 3, x, font.getLineHeight() + 2);
	    renderer.line(x + 1, 3, x + 1, font.getLineHeight() + 2);
	}
	renderer.end();
	batch.begin();
	font.setColor(Color.BLACK);
	font.draw(batch, text.subSequence(start, end), WIDTH_PADDING + getX(), 3 + getY());
	batch.end();
    }

    private float getWidthWithoutPaddings() {
	return getWidth() - WIDTH_PADDING * 2;
    }

    public void setTextColor(Color textColor) {
	this.textColor = textColor;
    }

    public CharSequence getCharSequence() {
	return text;
    }

    public String getText() {
	return text.toString();
    }

    public void setText(CharSequence string) {
	text = new StringBuilder(string);
	currentPosition = text.length();
    }

    @Override
    public void clear() {
	text.setLength(0);
    }

    @Override
    protected void activated() {
	super.activated();
	getStage().setKeyboardFocus(null);
	keyboard.show(false);
//	System.out.println(text + "; Length: " + text.length());
	if (currentOverflow != null) {
	    currentOverflow.activated();
	    currentOverflow = null;
	}
    }

}
