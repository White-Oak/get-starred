package me.oak.getstarred.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
	TiledLayer tiledLayer = new TiledLayer(tileableTexture, 100, 100);
	tiledLayer.resetMap(1);
	TiledLayerElement tiledLayerElement = new TiledLayerElement(tiledLayer);
	tiledLayerElement.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	stage.addActor(tiledLayerElement);
    }

}
