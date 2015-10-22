package me.oak.getstarred.server.messages;

import com.esotericsoftware.kryonet.Connection;
import lombok.Getter;
import me.oak.getstarred.server.entites.User;
import me.oak.getstarred.server.replies.PlainReply;
import me.oak.getstarred.server.replies.Status;

/**
 *
 * @author White Oak
 */
public class ReadyMessage extends Message {

    private String digest;

    @Getter private final MessageType type = MessageType.READY;

    @Override
    public void process(Managers managers, Connection connection) {
	User user = managers.accountManager.getUser(digest);
	if (user == null) {
	    managers.kryonetServer.send(new PlainReply(Status.ERROR, "No user"), connection);
	} else {
	    managers.findingManager.ready(user);
	}
    }

}
