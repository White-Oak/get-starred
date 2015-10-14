package me.oak.getstarred.server.replies;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 *
 * @author White Oak
 */
@Value @AllArgsConstructor public class FindReply {

    private final Status status;
    private final String message;
    private final int userId;

    public FindReply(Status status, String message) {
	this.status = status;
	this.message = message;
	userId = -1;
    }

}
