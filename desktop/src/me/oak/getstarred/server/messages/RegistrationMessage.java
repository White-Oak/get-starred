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

    private MessageType type = MessageType.REGISTRATION_REQ;
    private Managers managers;

    public void process(Managers managers, Connection connection) {
    }

}
