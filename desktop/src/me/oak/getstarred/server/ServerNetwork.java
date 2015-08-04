package me.oak.getstarred.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import me.oak.getstarred.server.messages.Message;
import me.oak.getstarred.server.messages.MessageBar;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class ServerNetwork extends Listener {
    
    public static final Gson GSON = new Gson();
    
    private final HashMap<Integer, Connection> connections = new HashMap<>();
    private final Queue<MessagingBundle> messages = new LinkedList<>();
    private final Queue<MessagingBundle> toBeSent = new LinkedList<>();
    private final ServerContext context;
    private Server server;
    
    public void host() {
	try {
	    server = new Server(2048, 8192);
	    server.start();
	    server.bind(51460);
	    server.addListener(this);
	} catch (IOException ex) {
	    Log.error("server", "While trying to start server", ex);
	}
    }
    
    @Override
    public void connected(Connection connection) {
	super.connected(connection);
	connections.put(connection.getID(), connection);
    }
    
    @Override
    public void received(Connection connection, Object object) {
	if (object instanceof String) {
	    System.out.println("I got one!");
	    messages.add(new MessagingBundle((String) object, connection.getID()));
	} else {
	    Log.warn("server", "Have received message, which is not String: " + object);
	}
    }
    
    @Override
    public void disconnected(Connection connection) {
	connections.remove(connection.getID());
    }
    
    public void send(String json, int ID) {
	toBeSent.add(new MessagingBundle(json, ID));
    }
    
    public void processQueues() {
        //received
	while (!messages.isEmpty()) {
	    try {
		MessagingBundle poll = messages.poll();
		MessageBar fromJson = GSON.fromJson(poll.message, MessageBar.class);
                Log.debug("server", fromJson.getType() + " is a type!");
		Message.getMessage(poll.message, fromJson.getType()).process(context, poll.connectionID);
	    } catch (Exception exception) {
		Log.error("server", "While processing ", exception);
	    }
        }
        //sent
	while (!toBeSent.isEmpty()) {
	    MessagingBundle poll = toBeSent.poll();
	    server.sendToTCP(poll.connectionID, poll.message);
        }
    }
    
    @Value private class MessagingBundle {
	
	private String message;
	private int connectionID;
    }
}
