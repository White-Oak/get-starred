package me.oak.getstarred.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
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
	int[][] map;
	try (Input input = new Input(Gdx.files.internal("map.bin").read())) {
	    map = new Kryo().readObject(input, int[][].class);
	}
	TiledLayer tiledLayer = new TiledLayer(tileableTexture, map);
	TiledLayerElement tiledLayerElement = new TiledLayerElement(tiledLayer);
	tiledLayerElement.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	stage.addActor(tiledLayerElement);
    }

}
