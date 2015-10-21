package me.oak.getstarred.server.managers;

import com.esotericsoftware.kryonet.Connection;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import me.oak.getstarred.server.KryonetServer;
import me.oak.getstarred.server.entites.Session;
import me.oak.getstarred.server.entites.User;
import me.oak.getstarred.server.replies.*;
import me.whiteoak.minlog.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author oak
 */
@Service public class AccountManager {

    @Autowired private KryonetServer kryonetServer;
    private int usersCount = 0;
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Session> sessions = new HashMap<>();

    public User getUser(String digest) {
//	final Optional<Session> findByDigest = sessionRepository.findByDigest(digest);
//	if (findByDigest.isPresent()) {
//	    return findByDigest.get().getUser();
//	}
	return null;
    }

    public void tryRegister(String login, String password_digest, Connection connection) {
	RegisterReply registerReply;
	if (users.get(login) != null) {
	    registerReply = new RegisterReply(Status.ERROR, login + " is already registered");
	} else {
	    Log.info("server", login + " is registered");
	    User user = new User(login, password_digest);
	    usersCount++;
	    user.setId(usersCount);
	    users.put(login, user);
	    registerReply = new RegisterReply(Status.SUCCESS, login + " was registered");
	}
	kryonetServer.send(registerReply, connection);
    }

    public void tryLogin(String login, String password_digest, Connection connection) {
	User get = users.get(login);
	if (get != null) {
	    if (get.getPassword_digest().equals(password_digest)) {
		Log.info("server", login + " is logged in");
		LocalDateTime of = LocalDateTime.now().plusMonths(1);
		Date out = Date.from(of.atZone(ZoneId.systemDefault()).toInstant());
		Session session = new Session(get, login, out, connection);
		sessions.put(login, session);
		LoginReply loginReply = new LoginReply(Status.SUCCESS, login + " was logged in", login, get);
		kryonetServer.send(loginReply, connection);
	    } else {
		LoginReply loginReply = new LoginReply(Status.ERROR, "The password is incorrect");
		kryonetServer.send(loginReply, connection);
	    }
	} else {
	    System.out.println("kokoko");
	    LoginReply loginReply = new LoginReply(Status.ERROR, "There is no user with login " + login);
	    kryonetServer.send(loginReply, connection);
	}
    }

    public void tryLogout(String digest, Connection connection) {
	Session get = sessions.remove(digest);
	if (get != null) {
	    Reply reply = new PlainReply("You successfully logged out");
	    kryonetServer.send(reply, get.getConnection());
	} else {
	    kryonetServer.send(new PlainReply("There is no session with such a key"), connection);
	}
    }
}
