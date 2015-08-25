package me.oak.getstarred.server;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import me.oak.getstarred.server.spring.entites.Session;
import me.oak.getstarred.server.spring.entites.User;
import me.oak.getstarred.server.spring.replies.*;
import me.whiteoak.minlog.Log;

/**
 *
 * @author oak
 */
public class AccountManager {

    public final static AccountManager INSTANCE = new AccountManager();

    public RegisterReply tryRegister(String login, String password_digest) {
	final Optional<User> user = Main.main.repository.findByLogin(login);
	if (!user.isPresent()) {
	    Log.info("server", login + " is registered");
	    return new RegisterReply("success", login + " was registered");
	} else {
	    return new RegisterReply("failure", login + " is already registered");
	}
    }

    public LoginReply tryLogin(String login, String password_digest) {
	final Optional<User> users = Main.main.repository.findByLogin(login);
	if (users.isPresent()) {
	    User user = users.get();
	    if (user.getPassword_digest().equals(password_digest)) {
		Log.info("server", login + " is logged in");
		LocalDateTime of = LocalDateTime.now().plusMonths(1);
		Date out = Date.from(of.atZone(ZoneId.systemDefault()).toInstant());
		Session session = new Session(user, login, out);
		Main.main.sessionRepository.save(session);
		user.setCurrentSession(session);
		Main.main.repository.save(user);
		Log.info("server", "New ession is stored for " + login);
		return new LoginReply("success", login + " was logged in.", "sample");
	    } else {
		return new LoginReply("failure", "The password is incorrect", "sample");
	    }
	} else {
	    return new LoginReply("failure", "There is no user with login " + login, "sample");
	}
    }

    public Reply tryLogout(String digest) {
	Optional<Session> sessionOptional = Main.main.sessionRepository.findByDigest(digest);
	if (sessionOptional.isPresent()) {
	    Main.main.sessionRepository.delete(sessionOptional.get());
	    return new Reply("You successfully logged out");
	} else {
	    return new Reply("There is no session with such a key");
	}
    }
}
