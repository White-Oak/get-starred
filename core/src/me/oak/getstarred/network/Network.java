package me.oak.getstarred.network;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.ClientContext;
import me.oak.getstarred.network.messages.Message;
import me.oak.getstarred.network.messages.MessageBar;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class Network {

    private final ClientNetwork clientNetwork = new ClientNetwork();
    private final ClientContext context;

    public void send(Message message) {
	clientNetwork.send(Message.GSON.toJson(message));
    }

    public void sendQueued() {
	clientNetwork.sendQueued();
    }

    public void processReceived() {
	List<String> received = clientNetwork.getReceived();
	for (String json : received) {
	    MessageBar temp = Message.GSON.fromJson(json, MessageBar.class);
	    Message.getMessage(json, temp.getType()).process(context);
	}
    }
}
