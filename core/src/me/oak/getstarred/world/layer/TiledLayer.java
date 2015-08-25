package me.oak.getstarred.world.layer;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author LPzhelud use of this class approved by the author 09.11.2012 - 9:15 AM
 * @version 2.0 since 28.11.2014 - 18:50 PM
 * @version 2.1 since 29.11.2014 - 16:05 PM
 */
public final class TiledLayer extends Layer {

    @Getter private final int[][] map;//[x][y]
    private final Drawable drawable;
    @Getter private final int tileWidth, tileHeight;

    @Setter @Getter private int visibleWidthInBlocks, visibleHeightInBlocks;

    public TiledLayer(Tileable tileable, int widthInBlocks, int heightInBlocks) {
	super(widthInBlocks, heightInBlocks);
	if (!tileable.isCorrect()) {
	    throw new IllegalArgumentException("Image is malformed.");
	}
	this.tileHeight = tileable.getTileHeight();
	this.tileWidth = tileable.getTileWidth();
	drawable = tileable.chopImage();
	map = new int[widthInBlocks][heightInBlocks];

	resetMap(0);
    }

    public void setTile(int x, int y, int tileId) {
	map[x][y] = tileId;
    }

    public void setTileChecked(int x, int y, int tileId) {
	try {
	    map[x][y] = tileId;
	} catch (Exception e) {
// System.out.println("error at: " + x + ":" + y);
	}
    }

    public int getTile(int x, int y) {
	int tileId = 0;
	try {
	    tileId = map[x][y];
	} catch (Exception ex) {
	    System.out.println("Out of bounds (getTile)");
	}
	return tileId;
    }

    public void fillRectTile(int x, int y, int w, int h, int tileId) {
	for (int i = y; i < y + h; i++) {
	    for (int j = x; j < w + x; j++) {
		setTile(j, i, tileId);
	    }
	}
    }

//отрисовка слоя, при этом рисуются только помещающиеся на экран тайлы
    @Override
    protected void paintLayer(Contextable c) {
//	final int vW = visibleWidthInBlocks > 0 ? visibleWidthInBlocks : getWidthInBlocks();
//	final int vH = visibleHeightInBlocks > 0 ? visibleHeightInBlocks : getHeightInBlocks();
	final int vW = getWidthInBlocks();
	final int vH = getHeightInBlocks();
	for (int i = 0; i < vW; i++) {
	    for (int j = 0; j < vH; j++) {
		drawable.paintTile(c, getX() + i * tileWidth, getY() + j * tileHeight,
			map[i + getBlocksX()][j + getBlocksY()]);
	    }
	}

    }

    public void resetMap(int tileId) {
	for (int i = 0; i < getWidthInBlocks(); i++) {
	    for (int j = 0; j < getHeightInBlocks(); j++) {
		map[i][j] = tileId;
	    }
	}

    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	for (int[] map1 : map) {
	    for (int n : map1) {
		sb.append(n).append(' ');
	    }
	    sb.append(System.lineSeparator());
	}
	return sb.toString();
    }

}
