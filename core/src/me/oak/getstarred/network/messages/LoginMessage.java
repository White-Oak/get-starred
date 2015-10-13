package me.oak.getstarred.network.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.network.ClientNetwork;
import me.oak.getstarred.server.replies.LoginReply;

/**
 *
 * @author White Oak
 */
@Getter @RequiredArgsConstructor public class LoginMessage extends Message {

    private final String login;
    private final String password_digest;

    private final MessageType type = MessageType.LOGIN_REQ;

    @Override
    public LoginReply process(ClientNetwork network) {
	return network.login(login, password_digest);
    }
}
