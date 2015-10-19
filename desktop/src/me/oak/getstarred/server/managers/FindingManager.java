package me.oak.getstarred.server.managers;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import me.oak.getstarred.server.entites.User;
import me.oak.getstarred.server.entites.UserRepository;
import me.oak.getstarred.server.replies.*;
import me.whiteoak.minlog.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author White Oak
 */
@Service public class FindingManager {

    private final Queue<User> finders = new ConcurrentLinkedQueue<>();
    @Autowired private UserRepository userRepository;
    @Autowired private LobbyManager lobbyManager;

    public FindReply find(User user) {
	Log.info("server", user.getLogin() + " wants to find a match");
	if (user.isLookingForMatch()) {
	    return new FindReply(Status.ERROR, "Still waiting");
	} else if (user.isInLobby()) {
	    return new FindReply(Status.SUCCESS, "Other user is already found", lobbyManager.getOtherUser(user));
	} else if (finders.isEmpty()) {
	    finders.add(user);
	    user.setLookingForMatch(true);
	    userRepository.save(user);
	    return new FindReply(Status.ERROR, "You're added to a queue");
	} else {
	    User poll = finders.poll();
	    poll.setLookingForMatch(false);
	    lobbyManager.createLobby(poll, user);
	    return new FindReply(Status.SUCCESS, "Other user is found", poll);
	}
    }

    public ReadyReply ready(User user) {
	boolean readyUp = lobbyManager.readyUp(user);
	if (readyUp) {
	    return new ReadyReply(Status.SUCCESS, "Your match is ready", null);
	} else {
	    return new ReadyReply(Status.ERROR, "Your match is not yet ready", null);
	}
    }
}
