package me.oak.getstarred.server;

import me.oak.getstarred.server.spring.entites.User;
import me.oak.getstarred.server.spring.entites.UserRepository;
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

    public static void main(String args[]) {
	SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	repository.save(new User("Oak", "1234"));
	repository.save(new User("Remi", "4321"));

	System.out.println("Users found with findAll():");
	System.out.println("-------------------------------");
	for (User user : repository.findAll()) {
	    System.out.println(user);
	}
	System.out.println();
	User customer = repository.findOne(1);
	System.out.println("User found with findOne(1L):");
	System.out.println("--------------------------------");
	System.out.println(customer);
	System.out.println();
	System.out.println("Customer found with findByLogin('Oak'):");
	System.out.println("--------------------------------------------");
	repository.findByLogin("Oak").forEach(System.out::println);
    }
}
