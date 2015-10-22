package me.oak.getstarred.server.managers;

import java.util.HashMap;
import java.util.Map;
import me.oak.getstarred.server.KryonetServer;
import me.oak.getstarred.server.Lobby;
import me.oak.getstarred.server.entites.User;
import me.oak.getstarred.server.replies.ReadyReply;
import me.oak.getstarred.server.replies.Status;
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
public class LobbyManager {

    private final Map<User, Lobby> mapping = new HashMap<>();
    @Autowired private KryonetServer kryonetServer;

    public void createLobby(User one, User two) {
	one.setInLobby(true);
	two.setInLobby(true);
//	userRepository.save(one);
//	userRepository.save(two);
	final Lobby lobby = new Lobby(one, two);
	addLobby(lobby);
    }

    public void readyUp(User user) {
	final boolean readyUp = findLobby(user).readyUp(user);
	if (readyUp) {

	}
    }

    private void addLobby(Lobby lobby) {
	mapping.put(lobby.getFirst(), lobby);
	mapping.put(lobby.getSecond(), lobby);
    }

    public User getOtherUser(User user) {
	Lobby findLobby = findLobby(user);
	if (findLobby != null) {
	    return findLobby.getOther(user);
	}
	return null;
    }

    private Lobby findLobby(User user) {
	return mapping.get(user);
    }

    @Scheduled(fixedDelay = 1000L)
    private void checkForReadiness() {
	mapping.values().stream()
		.distinct()
		.filter(value -> value.isReady())
		.forEach(value -> {
		    try {
			final ReadyReply readyReply = new ReadyReply(Status.SUCCESS, "Your match is ready", null);
			mapping.remove(value.getFirst());
			mapping.remove(value.getSecond());
			kryonetServer.send(readyReply, value.getFirst().getCurrentSession().getConnection());
			kryonetServer.send(readyReply, value.getSecond().getCurrentSession().getConnection());
		    } catch (StackOverflowError e) {
			System.out.println("KEK");
		    }
		});
    }
}
