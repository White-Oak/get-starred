package me.oak.getstarred.network.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.network.ClientNetwork;
import me.oak.getstarred.server.replies.RegisterReply;

/**
 *
 * @author White Oak
 */
@Getter @RequiredArgsConstructor public class RegistrationMessage extends Message {

    private final String login;
    private final String password_digest;

    private final MessageType type = MessageType.REGISTRATION_REQ;

    @Override
    public RegisterReply process(ClientNetwork network) {
	return network.register(login, password_digest);
    }

}
