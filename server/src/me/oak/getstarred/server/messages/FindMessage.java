package me.oak.getstarred.server.messages;

import com.esotericsoftware.kryonet.Connection;
import lombok.Getter;
import me.oak.getstarred.server.entites.User;
import me.oak.getstarred.server.replies.FindReply;
import me.oak.getstarred.server.replies.Status;

/**
 *
 * @author White Oak
 */
@Getter public class FindMessage extends Message {

    private String digest;
    private final MessageType type = MessageType.FIND;

    @Override
    public void process(Managers managers, Connection connection) {
	User user = managers.accountManager.getUser(digest);
	if (user == null) {
	    managers.kryonetServer.send(new FindReply(Status.ERROR, "No user"), connection);
	} else {
	    managers.findingManager.find(user);
	}
    }
}
