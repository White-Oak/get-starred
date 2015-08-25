package me.oak.getstarred.server;

import java.util.List;
import me.oak.getstarred.server.spring.entites.User;
import me.oak.getstarred.server.spring.entites.UserRepository;
import me.oak.getstarred.server.spring.replies.LoginReply;
import me.oak.getstarred.server.spring.replies.RegisterReply;
import me.whiteoak.minlog.Log;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author oak
 */
public class AccountManager {

    public final static AccountManager INSTANCE = new AccountManager();
    @Autowired UserRepository repository;

    public RegisterReply tryRegister(String login, String password_digest) {
	if (repository.findByLogin(login).isEmpty()) {
	    Log.info("server", login + " is registered");
	    return new RegisterReply("success", login + " was registered");
	} else {
	    return new RegisterReply("failure", login + " is already registered");
	}
    }

    public LoginReply tryLogin(String login, String password_digest) {
	final List<User> users = repository.findByLogin(login);
	if (!users.isEmpty()) {
	    User user = users.get(0);
	    if (user.getPassword_digest().equals(password_digest)) {
		Log.info("server", login + " is logged in");
		return new LoginReply("success", login + " was logged in.", "sample");
	    } else {
		return new LoginReply("failure", "The password is incorrect", "sample");
	    }
	} else {
	    return new LoginReply("failure", "There is no user with login " + login, "sample");
	}
    }

}
