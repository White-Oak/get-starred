package me.oak.getstarred.world.layer.fake;

import me.oak.getstarred.world.layer.Drawable;
import me.oak.getstarred.world.layer.Tileable;

public class TileableFake implements Tileable {

    @Override
    public boolean isCorrect() {
	return true;
    }

    @Override
    public Drawable chopImage() {
	return new DrawableFake();
    }

    @Override
    public int getTileWidth() {
	return 0;
    }

    @Override
    public int getTileHeight() {
	return 0;
    }

}
