package me.oak.getstarred.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.oak.getstarred.MeltingPoint;

public class DesktopLauncher {

    public static void main(String[] arg) {
	LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	config.width = 1280;
	config.height = 720;
	new LwjglApplication(new MeltingPoint(), config);
    }
}
