package me.oak.getstarred.server.replies;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author White Oak
 */
public class ReadyReply extends Reply {

    private Status status;
    private MatchReady matchReady;
    @Getter private final Reply.Type type = Reply.Type.MATCH_READY;

    public ReadyReply(Status status, String message, MatchReady matchReady) {
	super(message);
	this.status = status;
	this.matchReady = matchReady;
    }

    @RequiredArgsConstructor public static class MatchReady {

	private final Deck[] decks;

	public MatchReady(Deck one, Deck two) {
	    this(new Deck[]{one, two});
	}

	private class Deck {

	}
    }
}
