package me.oak.getstarred.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.io.IOException;
import me.riseremi.mreader.StarredMap;
import me.riseremi.mreader.WrongFormatException;
import me.whiteoak.minlog.Log;
import spaceisnear.game.layer.TiledLayer;
import spaceisnear.game.layer.gdx.TileableTexture;
import spaceisnear.game.ui.TiledLayerElement;
import spaceisnear.starting.ui.ScreenImprovedGreatly;

/**
 *
 * @author White Oak
 */
public class MatchScreen extends ScreenImprovedGreatly {

    @Override
    public void create() {
	Texture texture = new Texture(Gdx.files.internal("new_tiles.png"));
	TileableTexture tileableTexture = new TileableTexture(texture, 32, 32);
	int[][] map = null;
	try {
	    map = new StarredMap(Gdx.files.internal("map.m").reader()).getBackgroundLayer();
	} catch (IOException | WrongFormatException ex) {
	    Log.error("client", "While trying to read a map", ex);
	    Gdx.app.exit();
	}
	TiledLayer tiledLayer = new TiledLayer(tileableTexture, map);
	TiledLayerElement tiledLayerElement = new TiledLayerElement(tiledLayer);
	tiledLayerElement.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	stage.addActor(tiledLayerElement);
    }

}
