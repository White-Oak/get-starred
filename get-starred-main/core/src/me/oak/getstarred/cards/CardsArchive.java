package me.oak.getstarred.cards;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author White Oak
 */
public class CardsArchive {

    private final Map<Integer, Card> map = new HashMap<>();

    public void load() {
	Reader reader = Gdx.files.internal("cards.json").reader("UTF-8");
	Card[] fromJson = new Gson().fromJson(reader, Card[].class);
	for (Card card : fromJson) {
	    map.put(card.getId(), card);
	}
    }
}
