package me.oak.getstarred.server.replies;

import lombok.Getter;

/**
 *
 * @author White Oak
 */
public class LoginReply extends Reply {

    private final Status status;
    private final String digest;
    private final User user;
    @Getter private final Type type = Type.LOGIN;

    public LoginReply(Status status, String message, String digest, me.oak.getstarred.server.entites.User user) {
	super(message);
	this.status = status;
	this.digest = digest;
	this.user = User.hideUnwanted(user);
    }

    public LoginReply(Status status, String message) {
	this(status, message, null, null);
    }

}
