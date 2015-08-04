package me.oak.getstarred.server.messages;

import lombok.Getter;

/**
 *
 * @author oak
 */
@Getter public class NewDeckMessage extends Message {
    private final String name;
    private final int[] cards;
    private final String playerid;

    public NewDeckMessage(String name, int[] cards, String playerid) {
        super(MessageType.NEW_DECK);
        this.name = name;
        this.cards = cards;
        this.playerid = playerid;
    }

}
