package me.oak.getstarred.server.messages;

import com.esotericsoftware.kryonet.Connection;
import lombok.Getter;

/**
 *
 * @author White Oak
 */
@Getter public class LoginMessage extends Message {

    private String login;
    private String password_digest;

    private final MessageType type = MessageType.LOGIN_REQ;

    @Override
    public void process(Managers managers, Connection connection) {
	managers.accountManager.tryLogin(login, password_digest, connection);
    }
}
