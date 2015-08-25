package me.oak.getstarred.world.layer;

/**
 *
 * @author White Oak
 */
public interface Tileable {

    public Drawable chopImage();

    public int getTileWidth();

    public int getTileHeight();

    public boolean isCorrect();

}
