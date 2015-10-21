package me.oak.getstarred.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import java.io.IOException;
import javax.security.auth.login.LoginException;
import me.oak.getstarred.MeltingPoint;

public class DesktopLauncher {

    public static void main(String[] arg) throws IOException, LoginException {
	LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//	config.fullscreen = true;
	config.width = 1280;
	config.height = 720;
	new LwjglApplication(new MeltingPoint(), config);
    }
}
