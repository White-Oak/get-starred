package me.oak.getstarred.world.layer;

import lombok.*;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public abstract class Layer {

    @Getter private final int widthInBlocks, heightInBlocks;
    @Getter @Setter private int blocksX, blocksY;
    @Getter @Setter private int x, y;

    public void move(int deltaX, int deltaY) {
	x += deltaX;
	y += deltaY;
    }

    public final void paintComponent(Contextable c) {
	paintLayer(c);
    }

    protected abstract void paintLayer(Contextable c);

    public boolean inBounds(int blocksX, int blocksY) {
	return blocksX > 0 && blocksY > 0 && blocksX < widthInBlocks && blocksY < heightInBlocks;
    }
}
