package me.oak.getstarred.network;

import com.sun.jersey.api.client.*;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.util.*;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author White Oak
 */
public final class ClientNetwork {

    private final Queue<String> toBeSent = new LinkedList<>();
    private final Queue<String> toBeProcessed = new LinkedList<>();

    private final Client client = new Client();
    private final WebResource userResource = client.resource("http://localhost:8080/users");
    private final WebResource sessionResource = client.resource("http://localhost:8080/sessions");

    public ClientResponse register(String login, String password) {
	MultivaluedMap formData = new MultivaluedMapImpl();
	formData.add("login", login);
	formData.add("password", password);
	return userResource.post(ClientResponse.class, formData);
    }

    public ClientResponse login(String login, String password) {
	MultivaluedMap formData = new MultivaluedMapImpl();
	formData.add("login", login);
	formData.add("password", password);
	return sessionResource.post(ClientResponse.class, formData);
    }

    /**
     * Doesn't actually send, queueing it for sending via sendQueued().
     *
     * @param json
     */
    public void send(String json) {
	toBeSent.add(json);
    }

    /**
     * Sends all previously queued messages.
     */
    public void sendQueued() {
	while (!toBeSent.isEmpty()) {
	    System.out.println("I sent one!");
	}
    }

    public List<String> getReceived() {
	LinkedList<String> list = new LinkedList<>();
	while (!toBeProcessed.isEmpty()) {
	    list.add(toBeProcessed.poll());
	}
	return list;
    }
}
