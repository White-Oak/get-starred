package me.oak.getstarred.server;

import me.oak.getstarred.server.entites.User;

/**
 *
 * @author White Oak
 */
public class Match {

    final User[] users = new User[2];

    public Match(User one, User two) {
	users[0] = one;
	users[1] = two;
    }

    public Match(Lobby lobby) {
	users[0] = lobby.getFirst();
	users[1] = lobby.getSecond();
    }

    public User getOther(User user) {
	return users[users[0].equals(user) ? 1 : 0];
    }

    public User getFirst() {
	return users[0];
    }

    public User getSecond() {
	return users[1];
    }

}
