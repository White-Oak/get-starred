package me.oak.getstarred.network.messages;

import lombok.Getter;
import me.oak.getstarred.network.ClientNetwork;
import me.oak.getstarred.server.replies.RegisterReply;

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

    @Override
    public RegisterReply process(ClientNetwork network) {
	return network.register(login, password_digest);
    }

}
