package me.oak.getstarred.server;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import me.oak.getstarred.server.entites.User;
import me.oak.getstarred.server.entites.UserRepository;
import me.oak.getstarred.server.replies.FindReply;
import me.oak.getstarred.server.replies.Status;
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

    public FindReply find(User user) {
	Log.info("server", user.getLogin() + " wants to find a match");
	if (user.isLookingForMatch()) {
	    return new FindReply(Status.ERROR, "Still waiting");
	} else if (finders.isEmpty()) {
	    finders.add(user);
	    user.setLookingForMatch(true);
	    userRepository.save(user);
	    return new FindReply(Status.ERROR, "You're added to a queue");
	} else {
	    User poll = finders.poll();
	    poll.setLookingForMatch(false);
	    return new FindReply(Status.SUCCESS, "Your matching is " + poll);
	}
    }

}
