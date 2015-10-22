package spaceisnear.game.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.RequiredArgsConstructor;
import spaceisnear.game.layer.TiledLayer;
import spaceisnear.game.layer.gdx.ContextableTexture;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class TiledLayerElement extends UIElement {

    private final TiledLayer tiledLayer;
    private final ContextableTexture contextableTexture = new ContextableTexture();

    @Override
    public void paint(Batch batch) {
	batch.begin();
	contextableTexture.setBatch(batch);
	tiledLayer.paintComponent(contextableTexture);
	batch.end();
    }

}
