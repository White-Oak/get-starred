package me.oak.getstarred.decks;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author oak
 */
@Getter
@RequiredArgsConstructor
public class Deck {

    private final int id;
    private final String name;
    private final int[] cards;
}
