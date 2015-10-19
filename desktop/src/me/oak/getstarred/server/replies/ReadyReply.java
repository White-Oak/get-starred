package me.oak.getstarred.server.replies;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import me.oak.getstarred.server.entites.Deck;

/**
 *
 * @author White Oak
 */
@Value public class ReadyReply {

    private Status status;
    private String message;
    private MatchReady matchReady;

    @RequiredArgsConstructor public static class MatchReady {

	private final Deck[] decks;

	public MatchReady(Deck one, Deck two) {
	    this(new Deck[]{one, two});
	}
    }
}
