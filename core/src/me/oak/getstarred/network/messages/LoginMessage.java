package me.oak.getstarred.network.messages;

import lombok.Getter;
import me.oak.getstarred.network.ClientNetwork;
import me.oak.getstarred.server.replies.LoginReply;

/**
 *
 * @author White Oak
 */
@Getter public class LoginMessage extends Message {

    private final String login;
    private final String password_digest;

    public LoginMessage(String login, String password_digest) {
	super(MessageType.LOGIN_REQ);
	this.login = login;
	this.password_digest = password_digest;
    }

    @Override
    public LoginReply process(ClientNetwork network) {
	return network.login(login, password_digest);
    }
}
