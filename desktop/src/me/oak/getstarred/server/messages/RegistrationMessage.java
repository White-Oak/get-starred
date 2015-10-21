package me.oak.getstarred.server.messages;

import com.esotericsoftware.kryonet.Connection;
import lombok.Getter;

/**
 *
 * @author White Oak
 */
@Getter public class RegistrationMessage extends Message {

    private String login;
    private String password_digest;

    private final MessageType type = MessageType.REGISTRATION_REQ;

    @Override
    public void process(Managers managers, Connection connection) {
	managers.accountManager.tryRegister(login, password_digest, connection);
    }

}
