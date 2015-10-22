package me.oak.getstarred.server.managers;

import me.oak.getstarred.server.Lobby;
import me.oak.getstarred.server.Match;

/**
 *
 * @author White Oak
 */
public class MatchManager {

    public Match createMatch(Lobby lobby) {
	return new Match(lobby);
    }
}
