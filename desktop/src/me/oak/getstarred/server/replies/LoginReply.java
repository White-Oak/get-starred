package me.oak.getstarred.server.replies;

import lombok.Value;

/**
 *
 * @author White Oak
 */
@Value public class LoginReply {

    private final Status status;
    private final String message;
    private final String digest;
    private final User user;

    public LoginReply(Status status, String message, String digest, me.oak.getstarred.server.entites.User user) {
	this.status = status;
	this.message = message;
	this.digest = digest;
	this.user = User.hideUnwanted(user);
    }

    public LoginReply(Status status, String message) {
	this(status, message, null, null);
    }

}
