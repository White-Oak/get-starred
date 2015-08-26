package me.oak.getstarred.server;

import me.oak.getstarred.server.replies.Reply;
import me.oak.getstarred.server.replies.RegisterReply;
import me.oak.getstarred.server.replies.LoginReply;
import me.oak.getstarred.server.entites.User;
import me.oak.getstarred.server.entites.UserRepository;
import me.oak.getstarred.server.entites.SessionRepository;
import me.oak.getstarred.server.entites.Session;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
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

    public RegisterReply tryRegister(String login, String password_digest) {
	final Optional<User> user = userRepository.findByLogin(login);
	if (!user.isPresent()) {
	    Log.info("server", login + " is registered");
	    return new RegisterReply("success", login + " was registered");
	} else {
	    return new RegisterReply("failure", login + " is already registered");
	}
    }

    public LoginReply tryLogin(String login, String password_digest) {
	final Optional<User> users = userRepository.findByLogin(login);
	if (users.isPresent()) {
	    User user = users.get();
	    if (user.getPassword_digest().equals(password_digest)) {
		Log.info("server", login + " is logged in");
		LocalDateTime of = LocalDateTime.now().plusMonths(1);
		Date out = Date.from(of.atZone(ZoneId.systemDefault()).toInstant());
		Session session = new Session(user, login, out);
		sessionRepository.save(session);
		//deleting current session if present
		if (user.getCurrentSession() != null) {
		    sessionRepository.delete(user.getCurrentSession());
		}
		user.setCurrentSession(session);
		userRepository.save(user);
		Log.info("server", "New session is stored for " + login);
		return new LoginReply("success", login + " was logged in", login);
	    } else {
		return new LoginReply("failure", "The password is incorrect");
	    }
	} else {
	    return new LoginReply("failure", "There is no user with login " + login);
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
