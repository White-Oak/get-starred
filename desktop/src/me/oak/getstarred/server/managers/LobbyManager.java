package me.oak.getstarred.server.managers;

import java.util.HashMap;
import java.util.Map;
import me.oak.getstarred.server.Lobby;
import me.oak.getstarred.server.entites.User;

/**
 *
 * @author White Oak
 */
public class LobbyManager {

    private final Map<User, Lobby> mapping = new HashMap<>();

    public void createLobby(User one, User two) {
	one.setInLobby(true);
	two.setInLobby(true);
//	userRepository.save(one);
//	userRepository.save(two);
	final Lobby lobby = new Lobby(one, two);
	addLobby(lobby);
    }

    public boolean readyUp(User user) {
	final boolean readyUp = findLobby(user).readyUp(user);
	if (readyUp) {

	}
	return readyUp;
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
}
