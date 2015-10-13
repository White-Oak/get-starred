package me.oak.getstarred.server;

import java.util.HashMap;
import java.util.Map;
import me.oak.getstarred.server.entites.User;
import me.oak.getstarred.server.entites.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author White Oak
 */
@Service public class LobbyManager {

    private final Map<User, Lobby> mapping = new HashMap<>();
    @Autowired private UserRepository userRepository;

    public void createLobby(User one, User two) {
	one.setInLobby(true);
	two.setInLobby(true);
	userRepository.save(one);
	userRepository.save(two);
	final Lobby lobby = new Lobby(one, two);
	addLobby(lobby);
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
