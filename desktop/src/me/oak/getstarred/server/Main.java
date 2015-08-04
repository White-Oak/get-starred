package me.oak.getstarred.server;

import me.whiteoak.minlog.Log;

/**
 *
 * @author White Oak
 */
public class Main {

    public static void main(String args[]) {
	Log.info("server", "GetStarred server! Version: " + VersionCode.VERSION + "-b" + VersionCode.BUILD_NUMBER);
	ServerCore serverCore = new ServerCore();
	Thread thread = new Thread(serverCore, "Main server loop");
	thread.start();
    }
}
