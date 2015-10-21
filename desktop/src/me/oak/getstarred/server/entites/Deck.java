package me.oak.getstarred.server.entites;

import lombok.*;

/**
 *
 * @author White Oak
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Deck {

    private int id;
    private String name;
    private int[] cards;
    private User user;

    public Deck(String name, int... card) {
	this.name = name;
	cards = card;
    }

}
