package me.oak.getstarred.server.messages;

import lombok.Getter;

/**
 *
 * @author oak
 */
@Getter public class ReadyTFMessage extends Message {

    private final String playerid;
    private final int deck;

    public ReadyTFMessage(String playerid, int deck) {
        super(MessageType.READY_TO_FIGHT);
        this.playerid = playerid;
        this.deck = deck;
    }

}
