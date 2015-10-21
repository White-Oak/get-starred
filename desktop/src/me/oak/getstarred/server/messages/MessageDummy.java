package me.oak.getstarred.server.messages;

import com.esotericsoftware.kryonet.Connection;
import lombok.Getter;

/**
 *
 * @author White Oak
 */
public class MessageDummy extends Message {

    @Getter private MessageType type;

    @Override
    public void process(Managers managers, Connection connection) {
	throw new RuntimeException("Method process() in class MessageDummy is not implemented yet!");
    }

}
