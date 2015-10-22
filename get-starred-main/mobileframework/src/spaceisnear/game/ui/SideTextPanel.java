package spaceisnear.game.ui;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import lombok.Getter;

/**
 *
 * @author White Oak
 */
public class SideTextPanel extends SidePanel {

    @Getter private final TextField textField;

    public SideTextPanel() {
	textField = new TextField();
	textField.setBounds(EXTRA_WIDTH, getHeight() - textField.getHeight(),
		MAIN_WIDTH, textField.getHeight());
	addActor(textField);
	textField.setActivationListener(actor -> activated());
    }

    @Override
    protected void resetHidden() {
	super.resetHidden();
	if (isHidden()) {
	    textField.addAction(Actions.sequence(Actions.touchable(Touchable.disabled),
		    Actions.delay(0.2f),
		    Actions.visible(false)));
	} else {
	    textField.addAction(Actions.sequence(Actions.visible(true),
		    Actions.delay(0.2f),
		    Actions.touchable(Touchable.enabled)));
	}
    }

    @Override
    protected void activated() {
	super.activated();
	textField.clear();
    }

}
