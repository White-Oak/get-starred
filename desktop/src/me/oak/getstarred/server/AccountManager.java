package me.oak.getstarred.server;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import me.oak.getstarred.server.spring.entites.*;
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
    @Autowired SessionRepository sessionRepository;

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
		LocalDateTime of = LocalDateTime.now().plusMonths(1);
		Date out = Date.from(of.atZone(ZoneId.systemDefault()).toInstant());
		Session session = new Session(user, login, out);
		sessionRepository.save(session);
		user.setCurrentSession(session);
		repository.save(user);
		Log.info("server", "New ession is stored for " + login);
		return new LoginReply("success", login + " was logged in.", "sample");
	    } else {
		return new LoginReply("failure", "The password is incorrect", "sample");
	    }
	} else {
	    return new LoginReply("failure", "There is no user with login " + login, "sample");
	}
    }

}
