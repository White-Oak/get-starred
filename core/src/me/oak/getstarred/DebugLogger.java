package me.oak.getstarred;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.whiteoak.minlog.Log;
import spaceisnear.game.ui.SidePanel;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class DebugLogger extends Log.Logger {

    @Getter private final SidePanel panel = new SidePanel();

    {
	panel.setExtraY(200);
    }

    @Override
    protected void print(CharSequence message, String category) {
	panel.add(message);
    }

}
