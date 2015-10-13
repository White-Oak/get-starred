package me.oak.getstarred.network.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.network.ClientNetwork;
import me.oak.getstarred.server.replies.FindReply;

/**
 *
 * @author White Oak
 */
@Getter @RequiredArgsConstructor public class FindMessage extends Message {

    private final String digest;
    private final MessageType type = MessageType.FIND;

    @Override
    public FindReply process(ClientNetwork network) {
	return network.find(digest);
    }
}
