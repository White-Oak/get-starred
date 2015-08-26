package me.oak.getstarred.network;

import java.util.LinkedList;
import java.util.Queue;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.ClientContext;
import me.oak.getstarred.network.messages.Message;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class Network {

    private final ClientNetwork clientNetwork = new ClientNetwork();
    private final ClientContext context;
    private final Queue<Message> queueSent = new LinkedList<>();
    private final Queue<Message> queueReceived = new LinkedList<>();

    public void send(Message message) {
	queueSent.add(message);
    }

    public void sendQueued() {
	while (!queueSent.isEmpty()) {
	    Message msg = queueSent.poll();
	}
    }

    public void processReceived() {
    }
}
