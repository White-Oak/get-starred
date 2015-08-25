package spaceisnear.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import lombok.Getter;
import lombok.Setter;
import static spaceisnear.game.ui.UIElement.font;
import spaceisnear.game.ui.context.ContextMenu;
import spaceisnear.game.ui.context.ContextMenuItemable;

/**
 *
 * @author White Oak
 */
public final class MenuItem {

    private static final int HEIGHT_PADDING = 10;

    @Getter private String label;
    @Getter private final ContextMenu menu;
    @Getter private boolean selected;
    @Setter private Color selectedColor = Color.GRAY;
    @Getter private int width;

    public MenuItem(Stage stage) {
	this("", stage);
    }

    public MenuItem(String name, Stage stage) {
	this.menu = new ContextMenu(name, stage);
	setLabel(name);
    }

    public void setLabel(String name) {
	this.label = name;
	menu.setLabel(name);
	//TODO
	GlyphLayout glyphLayout = new GlyphLayout(font, label);
	width = (int) glyphLayout.width + HEIGHT_PADDING * 2;
    }

    public void setActivationListener(ActivationListener activationListener) {
	menu.setActivationListener(activationListener);
    }

    public boolean add(String str) {
	return menu.add(str);
    }

    public boolean add(ContextMenuItemable e) {
	return menu.add(e);
    }

    public int paint(int x, int y, ShapeRenderer renderer) {
	if (selected) {
	    renderer.setColor(selectedColor);
	    renderer.begin(ShapeRenderer.ShapeType.Filled);
	    renderer.rect(x, y - 2, width, font.getLineHeight() + 4);
	    renderer.end();
	}
	return width;
    }

    public void select(int x, int y) {
	selected = true;
	menu.setX(x);
	menu.setY(y);
	menu.show();
    }

    public void unselect() {
	selected = false;
	menu.hide();
    }

}
