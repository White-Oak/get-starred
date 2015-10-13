package me.oak.getstarred.server.chat;

import java.io.IOException;
import me.whiteoak.minlog.FileLogger;
import me.whiteoak.minlog.Log;

/**
 *
 * @author White Oak
 */
public class Main {

    public static void main(String[] args) throws IOException {
	Log.setLogger(new FileLogger());
	Log.info("chatserver", "Hi! This is a chat server for get-starred game");
	new ChatServer().host();
    }

}
