package me.oak.getstarred.server;

import lombok.Getter;
import me.oak.getstarred.server.entites.User;

/**
 *
 * @author White Oak
 */
@Getter public class Lobby {

    protected final User[] users = new User[2];
    private final boolean readiness[] = new boolean[2];

    public Lobby(User one, User two) {
	users[0] = one;
	users[1] = two;
    }

    public boolean readyUp(User user) {
	assert getFirst().equals(user) || getSecond().equals(user);
	readiness[getFirst().equals(user) ? 0 : 1] = true;
	return isReady();
    }

    public boolean isReady() {
	return readiness[0] && readiness[1];
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
