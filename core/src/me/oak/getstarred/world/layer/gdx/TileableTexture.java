package me.oak.getstarred.world.layer.gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.world.layer.Drawable;
import me.oak.getstarred.world.layer.Tileable;

@RequiredArgsConstructor public class TileableTexture implements Tileable {

    private final Texture image;
    @Getter private final int tileWidth, tileHeight;

    @Override
    public Drawable chopImage() {
	TextureRegion[][] split = TextureRegion.split(image, tileWidth, tileHeight);
	TextureRegion[] regions = new TextureRegion[split.length * split[0].length];
	int index = 0;
	for (TextureRegion[] textureRegions : split) {
	    for (TextureRegion textureRegion : textureRegions) {
		textureRegion.flip(false, true);
		regions[index++] = textureRegion;
	    }
	}
	return new DrawableTexture(regions);
    }

    @Override
    public boolean isCorrect() {
	return image.getWidth() / tileWidth * tileWidth == image.getWidth()
		&& image.getHeight() / tileHeight * tileHeight == image.getHeight();
    }

}
