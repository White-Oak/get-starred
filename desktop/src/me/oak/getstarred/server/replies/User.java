package me.oak.getstarred.server.replies;

import lombok.Value;

/**
 *
 * @author White Oak
 */
@Value
class User {

    int id;
    String login;

    public static User hideUnwanted(me.oak.getstarred.server.entites.User user) {
	return new User(user.getId(), user.getLogin());
    }
}
