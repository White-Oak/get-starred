package me.oak.getstarred.server.messages;

import com.esotericsoftware.kryonet.Connection;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public abstract class Message {

    public abstract MessageType getType();

    public final Class<? extends Message> getAssociatedClass() {
	switch (getType()) {
	    case FIND:
		return FindMessage.class;
	    case LOGIN_REQ:
		return LoginMessage.class;
	    case REGISTRATION_REQ:
		return RegistrationMessage.class;
	    case READY:
		return ReadyMessage.class;
	    default:
		return null;
	}
    }

    public abstract void process(Managers managers, Connection connection);
}
