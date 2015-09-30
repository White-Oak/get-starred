package me.oak.getstarred.network;

import java.util.*;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.ClientContext;
import me.oak.getstarred.network.messages.Message;
import me.oak.getstarred.server.replies.Reply;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class Network {

    private final ClientNetwork clientNetwork = new ClientNetwork();
    private final ClientContext context;
    private final Queue<Message> queueSent = new LinkedList<>();
    private final Queue<Reply> queueReceived = new LinkedList<>();

    public void queue(Message message) {
	queueSent.add(message);
    }

    public void sendQueued() {
	while (!queueSent.isEmpty()) {
	    queueReceived.add(queueSent.poll().process(clientNetwork));
	}
    }

    public Collection<Reply> processReceived() {
	if (queueReceived.isEmpty()) {
	    return null;
	}
	List<Reply> list = new LinkedList<>();
	while (!queueReceived.isEmpty()) {
	    final Reply poll = queueReceived.poll();
	    System.out.println(poll);
	    list.add(poll);
	}
	return list;
    }
}
