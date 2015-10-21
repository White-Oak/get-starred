package me.oak.getstarred.network;

import java.io.IOException;
import java.util.*;
import lombok.*;
import me.oak.getstarred.ClientContext;
import me.oak.getstarred.network.messages.Message;
import me.oak.getstarred.server.replies.Reply;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class Network {

    private final ClientContext context;
    private final Queue<Message> queueSent = new LinkedList<>();
    private final Queue<Reply> queueReceived = new LinkedList<>();
    private final KryonetClient kryonetClient = new KryonetClient();

    @Getter @Setter private String digest;

    public void start() {
	try {
	    kryonetClient.start();
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
    }

    public void queue(Message message) {
	queueSent.add(message);
    }

    public void sendQueued() {
	while (!queueSent.isEmpty()) {
	    kryonetClient.send(queueSent.poll());
	}
    }

    public Collection<Reply> processReceived() {
	if (kryonetClient.getReplies().isEmpty()) {
	    return null;
	}
	List<Reply> list = new LinkedList<>();
	while (!kryonetClient.getReplies().isEmpty()) {
	    final Reply poll = kryonetClient.getReplies().poll();
	    System.out.println(poll.getClass() + " " + poll);
	    list.add(poll);
	}
	return list;
    }
}
