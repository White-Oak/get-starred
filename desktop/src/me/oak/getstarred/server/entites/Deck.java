package me.oak.getstarred.server.entites;

import javax.persistence.*;
import lombok.*;

/**
 *
 * @author White Oak
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int[] cards;
    @OneToOne private User user;

    public Deck(String name, int... card) {
	this.name = name;
	cards = card;
    }

}
