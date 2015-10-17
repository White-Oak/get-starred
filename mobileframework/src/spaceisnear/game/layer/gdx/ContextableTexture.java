package spaceisnear.game.layer.gdx;

import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.Getter;
import lombok.Setter;
import spaceisnear.game.layer.Contextable;

public class ContextableTexture implements Contextable {

    @Setter @Getter private Batch batch;
}
