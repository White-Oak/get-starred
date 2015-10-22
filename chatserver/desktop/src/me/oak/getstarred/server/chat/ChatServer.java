package me.oak.getstarred.server.chat;

import com.esotericsoftware.kryonet.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import me.oak.getstarred.server.chat.Client;
import me.oak.getstarred.server.chat.messages.*;
import me.whiteoak.minlog.Log;

/**
 *
 * @author White Oak
 */
public class ChatServer extends Listener {

    private final Server server = new Server(4096, 1024);
    private final Map<Integer, Client> clientsMappings = new HashMap<>();
    private final Map<ChatLobby, ChatLobby> clientsLobbies = new HashMap<>();
    private final Map<Integer, Client> connectionClientsMapping = new HashMap<>();

    public void host() throws IOException {
	server.start();
	server.bind(51446, 51447);
	server.addListener(this);
	registerAll();
	Log.info("chatserver", "Server has started");
    }

    private void registerAll() {
	server.getKryo().register(HandshakeMessage.class);
	server.getKryo().register(ChatWithMessage.class);
	server.getKryo().register(MessageMessage.class);
	server.getKryo().register(ChatMessage.class);
    }

    @Override
    public void received(Connection connection, Object object) {
	super.received(connection, object);
	if (object instanceof Message) {
	    Message m = (Message) object;
	    switch (m.getType()) {
		case HANDSHAKE: {
		    HandshakeMessage hm = (HandshakeMessage) m;
		    final Client client = new Client(connection, hm.getId());
		    clientsMappings.put(hm.getId(), client);
		    connectionClientsMapping.put(connection.getID(), client);
		    Log.info("chatserver", hm.getId() + " has connected");
		}
		break;
		case CHAT_WITH: {
		    ChatWithMessage cwm = (ChatWithMessage) m;
		    Client get = connectionClientsMapping.get(connection.getID());
		    final int withId = cwm.getWithId();
		    getLobby(get.getId(), withId);
		}
		break;
		case MESSAGE: {
		    MessageMessage mm = (MessageMessage) m;
		    ChatLobby lobby = getLobby(mm.getChatMessage().getFrom(), mm.getChatMessage().getTo());
		    ChatMessage chatMessage = mm.getChatMessage();
		    final int from = chatMessage.getFrom();
		    final int to = chatMessage.getTo();
		    lobby.add(from, to, chatMessage.getString());
		    Client toClient = clientsMappings.get(to);
		    if (toClient != null) {
			Log.info("chatserver", "Message is " + chatMessage);
			server.sendToTCP(toClient.getConnection().getID(), mm);
		    } else {
			Log.info("chatserver", from + " tried to chat with " + to + " but the receipnt is missing.");
			final ChatMessage chatMessage1 = new ChatMessage(from, from, "Sorry, but your recipient isn't logged in");
			server.sendToTCP(connection.getID(), new MessageMessage(chatMessage1));
		    }
		}
		break;
	    }
	}
    }

    public ChatLobby getLobby(int from, final int to) {
	ChatLobby cl = clientsLobbies.get(new ChatLobby(from, to));
	if (cl == null) {
	    Log.info("chatserver", "New lobby from " + from + " to " + to);
	    cl = new ChatLobby(from, to);
	    clientsLobbies.put(cl, cl);
	}
	return cl;
    }

    @Override
    public void disconnected(Connection connection) {
	super.disconnected(connection);
	Client get = connectionClientsMapping.remove(connection.getID());
	if (get != null) {
	    clientsLobbies.remove(get.getId());
	}
    }

}
