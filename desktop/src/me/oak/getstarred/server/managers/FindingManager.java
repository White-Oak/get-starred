package me.oak.getstarred.server.managers;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import me.oak.getstarred.server.entites.User;
import me.oak.getstarred.server.replies.*;

/**
 *
 * @author White Oak
 */
public class FindingManager {

    private final Queue<User> finders = new ConcurrentLinkedQueue<>();

    public void find(User user) {
//	Log.info("server", user.getLogin() + " wants to find a match");
//	if (user.isLookingForMatch()) {
//	} else if (user.isInLobby()) {
//	} else {
//	    finders.add(user);
//	    user.setLookingForMatch(true);
//	    userRepository.save(user);
//	}
    }

    public ReadyReply ready(User user) {
//	boolean readyUp = lobbyManager.readyUp(user);
//	if (readyUp) {
//	    return new ReadyReply(Status.SUCCESS, "Your match is ready", null);
//	} else {
//	    return new ReadyReply(Status.ERROR, "Your match is not yet ready", null);
//	}
	return null;
    }

    private void checkWhatFound() {
//	while (!finders.isEmpty() && finders.size() > 1) {
//	    User one = finders.poll();
//	    User two = finders.poll();
//	    one.setLookingForMatch(false);
//	    two.setLookingForMatch(false);
//	    lobbyManager.createLobby(one, two);
//	    final String destination1 = "/finder/digest." + one.getCurrentSession().getDigest();
//	    final String destination2 = "/finder/digest." + two.getCurrentSession().getDigest();
//	    final FindReply findReply1 = new FindReply(Status.SUCCESS, "Other user is found", two);
//	    final FindReply findReply2 = new FindReply(Status.SUCCESS, "Other user is found", one);
//	    messagingTemplate.convertAndSend(destination1, findReply2);
//	    messagingTemplate.convertAndSend(destination1, findReply2);
//	}
    }
}
