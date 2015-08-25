package me.oak.getstarred.world.layer.gdx;

import com.badlogic.gdx.graphics.g2d.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.world.layer.Contextable;
import me.oak.getstarred.world.layer.Drawable;

@RequiredArgsConstructor public class DrawableTexture implements Drawable {

    @Getter private final TextureRegion[] regions;

    @Override
    public void paintTile(Contextable c, int x, int y, int id) {
	ContextableTexture ct = (ContextableTexture) c;
	Batch batch = ct.getBatch();
	batch.draw(regions[id], x, y);
    }

}
