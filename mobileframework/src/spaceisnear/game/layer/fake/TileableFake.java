package spaceisnear.game.layer.fake;

import spaceisnear.game.layer.Drawable;
import spaceisnear.game.layer.Tileable;

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
