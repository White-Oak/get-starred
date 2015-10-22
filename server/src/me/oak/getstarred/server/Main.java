package me.oak.getstarred.server;

import java.io.IOException;
import me.oak.getstarred.server.managers.AccountManager;
import me.whiteoak.minlog.FileLogger;
import me.whiteoak.minlog.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author White Oak
 */
@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired private KryonetServer kryonetServer;
    @Autowired private AccountManager accountManager;

    public static void main(String args[]) {
	Log.setLogger(new FileLogger());
	SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
	try {
	    kryonetServer.start();
	    Thread thread = new Thread(() -> {
		while (true) {
		    kryonetServer.processMessages();
		}
	    }, "messages processing");
	    thread.start();
	    accountManager.register("Oak", "1234");
	    accountManager.register("Remi", "4321");
	} catch (IOException ex) {
	    Log.error("server", "while trying to init kryonet", ex);
	    throw new RuntimeException(ex);
	}
    }
}
