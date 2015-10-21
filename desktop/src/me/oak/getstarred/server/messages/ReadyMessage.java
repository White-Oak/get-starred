package me.oak.getstarred.server.messages;

import com.esotericsoftware.kryonet.Connection;
import lombok.Getter;

/**
 *
 * @author White Oak
 */
public class ReadyMessage extends Message {

    private String digest;

    @Getter private final MessageType type = MessageType.READY;

    public void process(Managers managers, Connection connection) {
    }

}
