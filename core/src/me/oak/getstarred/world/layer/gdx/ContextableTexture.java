package me.oak.getstarred.world.layer.gdx;

import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.Getter;
import lombok.Setter;
import me.oak.getstarred.world.layer.Contextable;

public class ContextableTexture implements Contextable {

    @Setter @Getter private Batch batch;
}
