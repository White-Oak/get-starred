package me.oak.getstarred.network.messages;

import lombok.Getter;

/**
 *
 * @author White Oak
 */
@Getter public class RegistrationMessage extends Message {

    private final String login;
    private final String password_digest;

    public RegistrationMessage(String login, String password_digest) {
	super(MessageType.REGISTRATION_REQ);
	this.login = login;
	this.password_digest = password_digest;
    }

}
