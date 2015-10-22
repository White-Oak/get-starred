package me.oak.getstarred.server.managers;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import me.oak.getstarred.server.KryonetServer;
import me.oak.getstarred.server.entites.User;
import me.oak.getstarred.server.replies.FindReply;
import me.oak.getstarred.server.replies.Status;
import me.whiteoak.minlog.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author White Oak
 */
@Service
@EnableScheduling
public class FindingManager {

    private final Queue<User> finders = new ConcurrentLinkedQueue<>();
    @Autowired private LobbyManager lobbyManager;
    @Autowired private KryonetServer kryonetServer;

    public void find(User user) {
	Log.info("server", user.getLogin() + " wants to find a match");
	if (user.isLookingForMatch()) {
	} else if (user.isInLobby()) {
	} else {
	    finders.add(user);
	    user.setLookingForMatch(true);
	}
    }

    public void ready(User user) {
	lobbyManager.readyUp(user);
    }

    @Scheduled(fixedDelay = 1000L)
    private void checkWhatFound() {
	while (!finders.isEmpty() && finders.size() > 1) {
	    User one = finders.poll();
	    User two = finders.poll();
	    one.setLookingForMatch(false);
	    two.setLookingForMatch(false);
	    lobbyManager.createLobby(one, two);
	    final FindReply findReply1 = new FindReply(Status.SUCCESS, "Other user is found", two);
	    final FindReply findReply2 = new FindReply(Status.SUCCESS, "Other user is found", one);
	    kryonetServer.send(findReply1, one.getCurrentSession().getConnection());
	    kryonetServer.send(findReply2, two.getCurrentSession().getConnection());
	}
    }
}
