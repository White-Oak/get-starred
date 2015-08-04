package me.oak.getstarred.server.messages.replies;

import lombok.Getter;
import me.oak.getstarred.decks.Deck;
import me.oak.getstarred.server.messages.Message;
import me.oak.getstarred.server.messages.MessageType;

/**
 *
 * @author oak
 */
public class DecksReplyMessage extends Message {
    @Getter
    private final Deck[] decks;

    public DecksReplyMessage(Deck[] decks) {
        super(MessageType.DECKS_REQ_REPLY);
        this.decks = decks;
    }

}
