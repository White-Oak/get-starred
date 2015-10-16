package me.oak.getstarred.server.replies;

import lombok.Value;

/**
 *
 * @author White Oak
 */
@Value public class FindReply {

    private final Status status;
    private final String message;
    private final User user;

    public FindReply(Status status, String message, me.oak.getstarred.server.entites.User user) {
	this.status = status;
	this.message = message;
	this.user = User.hideUnwanted(user);
    }

    public FindReply(Status status, String message) {
	this.status = status;
	this.message = message;
	user = null;
    }

}
