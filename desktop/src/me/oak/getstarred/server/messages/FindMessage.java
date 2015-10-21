package me.oak.getstarred.server.messages;

import com.esotericsoftware.kryonet.Connection;
import lombok.Getter;

/**
 *
 * @author White Oak
 */
@Getter public class FindMessage extends Message {

    private String digest;
    private final MessageType type = MessageType.FIND;

    public void process(Managers managers, Connection connection) {
    }
}
