package me.oak.getstarred.server.spring.replies;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 *
 * @author White Oak
 */
@Value @AllArgsConstructor public class LoginReply {

    private final String status;
    private final String message;
    private final String digest;

    public LoginReply(String status, String message) {
	this(status, message, null);
    }

}
