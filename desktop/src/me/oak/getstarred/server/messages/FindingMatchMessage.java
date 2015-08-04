package me.oak.getstarred.server.messages;

import lombok.Getter;

/**
 *
 * @author oak
 */
public class FindingMatchMessage extends Message {

    @Getter private final String playerid;

    public FindingMatchMessage(String playerid) {
        super(MessageType.FINDING_MATCH);
        this.playerid = playerid;
    }

}
