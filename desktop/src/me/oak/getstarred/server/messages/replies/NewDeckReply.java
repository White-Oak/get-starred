package me.oak.getstarred.server.messages.replies;

import lombok.Getter;
import me.oak.getstarred.server.messages.Message;
import me.oak.getstarred.server.messages.MessageType;

/**
 *
 * @author oak
 */
public class NewDeckReply extends Message {

    @Getter private final String status;

    public NewDeckReply(String status) {
        super(MessageType.NEW_DECK_REPLY);
        this.status = status;
    }

}
