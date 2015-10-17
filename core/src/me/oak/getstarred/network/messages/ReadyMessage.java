package me.oak.getstarred.network.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.network.ClientNetwork;
import me.oak.getstarred.server.replies.Reply;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class ReadyMessage extends Message {

    private final String digest;

    @Getter private final MessageType type = MessageType.READY;

    @Override
    public Reply process(ClientNetwork network) {
	return network.ready(digest);
    }

}
