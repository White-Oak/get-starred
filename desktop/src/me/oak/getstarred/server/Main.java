package me.oak.getstarred.server;

import me.oak.getstarred.server.entites.User;
import me.oak.getstarred.server.entites.UserRepository;
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

    @Autowired private UserRepository repository;

    public static void main(String args[]) {
	Log.setLogger(new FileLogger());
	SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	final User oak = new User("Oak", "1234");
	repository.save(oak);
	repository.save(new User("Remi", "4321"));

	System.out.println("Users found with findAll():");
	System.out.println("-------------------------------");
	for (User user : repository.findAll()) {
	    System.out.println(user);
	}
	System.out.println("-------------------------------");
	System.out.println();
    }
}
