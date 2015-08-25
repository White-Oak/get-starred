package me.oak.getstarred;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import spaceisnear.game.ui.UIElement;

/**
 *
 * @author White Oak
 */
public class DebugActor extends Actor {

    private final BitmapFont font;

    public DebugActor() {
	FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("a song for jennifer.ttf"));
	FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	parameter.size = UIElement.determineFontSize();
	parameter.flip = true;

	font = generator.generateFont(parameter);
	generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
	font.setColor(Color.WHITE);

	font.draw(batch, "Build " + spaceisnear.VersionCode.BUILD_NUMBER, 10, 10);
	font.draw(batch, Gdx.graphics.getWidth() + " x " + Gdx.graphics.getHeight(), 10, 50);

    }

}
