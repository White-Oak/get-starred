package me.oak.getstarred.server;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import me.oak.getstarred.server.entites.*;
import me.oak.getstarred.server.replies.*;
import me.whiteoak.minlog.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author oak
 */
@Service
public class AccountManager {

    @Autowired private UserRepository userRepository;
    @Autowired private SessionRepository sessionRepository;

    public User getUser(String digest) {
	final Optional<Session> findByDigest = sessionRepository.findByDigest(digest);
	if (findByDigest.isPresent()) {
	    return findByDigest.get().getUser();
	}
	return null;
    }

    public RegisterReply tryRegister(String login, String password_digest) {
	final Optional<User> user = userRepository.findByLogin(login);
	if (!user.isPresent()) {
	    Log.info("server", login + " is registered");
	    return new RegisterReply(Status.SUCCESS, login + " was registered");
	} else {
	    return new RegisterReply(Status.ERROR, login + " is already registered");
	}
    }

    public LoginReply tryLogin(String login, String password_digest) {
	final Optional<User> userOpt = userRepository.findByLogin(login);
	if (userOpt.isPresent()) {
	    User user = userOpt.get();
	    if (user.getPassword_digest().equals(password_digest)) {
		Log.info("server", login + " is logged in");
		//deleting current session if present
		if (user.getCurrentSession() != null) {
		    sessionRepository.delete(user.getCurrentSession());
		}
		LocalDateTime of = LocalDateTime.now().plusMonths(1);
		Date out = Date.from(of.atZone(ZoneId.systemDefault()).toInstant());
		Session session = new Session(user, login, out);
		sessionRepository.save(session);
		user.setCurrentSession(session);
		userRepository.save(user);
		Log.info("server", "New session is stored for " + login);
		return new LoginReply(Status.SUCCESS, login + " was logged in", login, user.getId());
	    } else {
		return new LoginReply(Status.ERROR, "The password is incorrect");
	    }
	} else {
	    return new LoginReply(Status.ERROR, "There is no user with login " + login);
	}
    }

    public Reply tryLogout(String digest) {
	Optional<Session> sessionOptional = sessionRepository.findByDigest(digest);
	if (sessionOptional.isPresent()) {
	    sessionRepository.delete(sessionOptional.get());
	    return new Reply("You successfully logged out");
	} else {
	    return new Reply("There is no session with such a key");
	}
    }
}
