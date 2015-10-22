package me.oak.getstarred.server.replies;

import lombok.Getter;

/**
 *
 * @author White Oak
 */
public class FindReply extends Reply {

    private final Status status;
    private final User user;
    @Getter private final Reply.Type type = Reply.Type.FINDING;

    public FindReply(Status status, String message, me.oak.getstarred.server.entites.User user) {
	super(message);
	this.status = status;
	this.user = User.hideUnwanted(user);
    }

    public FindReply(Status status, String message) {
	this(status, message, null);
    }

}
