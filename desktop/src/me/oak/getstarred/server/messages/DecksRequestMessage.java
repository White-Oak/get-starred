package me.oak.getstarred.server.messages;

import lombok.Getter;

/**
 *
 * @author oak
 */
public class DecksRequestMessage extends Message {
    @Getter private final String playerid;

    public DecksRequestMessage(String playerid) {
        super(MessageType.DECKS_REQ);
        this.playerid = playerid;
    }

}
