package me.oak.getstarred.network.messages;

import lombok.Getter;
import me.oak.getstarred.ClientContext;

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

    @Override
    public void process(ClientContext context) {
	System.out.println(status + ": " + message);
    }

}
