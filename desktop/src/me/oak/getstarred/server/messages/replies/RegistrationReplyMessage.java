package me.oak.getstarred.server.messages.replies;

import lombok.Getter;
import me.oak.getstarred.server.messages.Message;
import me.oak.getstarred.server.messages.MessageType;

/**
 *
 * @author White Oak
 */
@Getter public class RegistrationReplyMessage extends Message {

    private final String status;
    private final String message;

    public RegistrationReplyMessage(String status, String message) {
	super(MessageType.REGISTRATION_REQ_REPLY);
	this.status = status;
	this.message = message;
    }

}
