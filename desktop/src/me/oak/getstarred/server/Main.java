package me.oak.getstarred.server;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import me.oak.getstarred.server.spring.entites.*;
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

    @Autowired UserRepository repository;
    @Autowired SessionRepository sessionRepository;

    public static void main(String args[]) {
	SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	final User oak = new User("Oak", "1234");
	repository.save(oak);
	repository.save(new User("Remi", "4321"));

	LocalDateTime of = LocalDateTime.now().plusMonths(1);
	Date out = Date.from(of.atZone(ZoneId.systemDefault()).toInstant());
	final Session session = new Session(oak, "sample", out);
	sessionRepository.save(session);

	System.out.println("Users found with findAll():");
	System.out.println("-------------------------------");
	for (User user : repository.findAll()) {
	    System.out.println(user);
	}
	System.out.println();

	System.out.println("Sessions found with findAll():");
	System.out.println("-------------------------------");
	for (Session user : sessionRepository.findAll()) {
	    System.out.println(user);
	}
	System.out.println();

    }
}
