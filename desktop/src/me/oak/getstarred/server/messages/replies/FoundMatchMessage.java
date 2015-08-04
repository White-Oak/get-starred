package me.oak.getstarred.server.messages.replies;

import lombok.Getter;
import me.oak.getstarred.decks.Deck;
import me.oak.getstarred.server.messages.Message;
import me.oak.getstarred.server.messages.MessageType;

/**
 *
 * @author oak
 */
@Getter public class FoundMatchMessage extends Message {

    private final String nickname;
    private final int level;
    private final String featuredMessage;
    private final Deck decks[];

    public FoundMatchMessage(String nickname, int level, String featuredMessage, Deck[] decks) {
        super(MessageType.FOUND_MATCH);
        this.nickname = nickname;
        this.level = level;
        this.featuredMessage = featuredMessage;
        this.decks = decks;
    }


}
