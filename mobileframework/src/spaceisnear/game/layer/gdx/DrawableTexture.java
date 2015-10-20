package spaceisnear.game.layer.gdx;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import spaceisnear.game.layer.Contextable;
import spaceisnear.game.layer.Drawable;

@RequiredArgsConstructor public class DrawableTexture implements Drawable {

    @Getter private final TextureRegion[] regions;

    @Override
    public void paintTile(Contextable c, int x, int y, int id) {
	if (id < 0 || id >= regions.length) {
	    return;
	}
	ContextableTexture ct = (ContextableTexture) c;
	Batch batch = ct.getBatch();
	batch.draw(regions[id], x, y);
    }

}
