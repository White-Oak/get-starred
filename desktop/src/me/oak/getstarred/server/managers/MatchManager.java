package me.oak.getstarred.server.managers;

import me.oak.getstarred.server.Lobby;
import me.oak.getstarred.server.Match;
import org.springframework.stereotype.Service;

/**
 *
 * @author White Oak
 */
@Service public class MatchManager {

    public Match createMatch(Lobby lobby) {
	return new Match(lobby);
    }
}
