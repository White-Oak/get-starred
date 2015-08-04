package me.oak.getstarred.server.messages.replies;

import me.oak.getstarred.server.messages.Message;
import me.oak.getstarred.server.messages.MessageType;

/**
 *
 * @author oak
 */
public class LogonMessage extends Message {
    private final String message;

    public LogonMessage(String message) {
        super(MessageType.LOGON);
        this.message = message;
    }

}
