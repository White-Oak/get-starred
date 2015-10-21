package me.oak.getstarred.server.replies;

import lombok.Getter;

/**
 *
 * @author White Oak
 */
@Getter public class RegisterReply extends Reply {

    private final Status status;
    private final Type type = Type.REGISTER;

    public RegisterReply(Status status, String message) {
	super(message);
	this.status = status;
    }

}
