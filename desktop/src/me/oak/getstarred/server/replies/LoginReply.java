package me.oak.getstarred.server.replies;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 *
 * @author White Oak
 */
@Value @AllArgsConstructor public class LoginReply {

    private final Status status;
    private final String message;
    private final String digest;
    private final int id;

    public LoginReply(Status status, String message) {
	this(status, message, null, -1);
    }

}
