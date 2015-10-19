package me.oak.getstarred.cards;

import me.oak.getstarred.server.entites.Deck;

/**
 *
 * @author White Oak
 */
public class CardArchive {

    public static Deck getDefaultDeck() {
	return new Deck("default", 1, 2, 3, 4, 5);
    }

}
