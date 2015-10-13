package me.oak.getstarred.server.chat;

import com.esotericsoftware.kryonet.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import me.oak.getstarred.server.chat.Client;
import me.oak.getstarred.server.chat.messages.HandshakeMessage;
import me.oak.getstarred.server.chat.messages.Message;
import me.whiteoak.minlog.Log;

/**
 *
 * @author White Oak
 */
public class ChatServer extends Listener {

    private final Server server = new Server(4096, 1024);
    private final Map<Integer, Client> clientsMappings = new HashMap<>();

    public void host() throws IOException {
	server.start();
	server.bind(51446, 51447);
	server.addListener(this);
	registerAll();
	Log.info("chatserver", "Server has started");
    }

    private void registerAll() {
	server.getKryo().register(HandshakeMessage.class);
    }

    @Override
    public void received(Connection connection, Object object) {
	if (object instanceof Message) {
	    Message m = (Message) object;
	    switch (m.getType()) {
		case HANDSHAKE:
		    HandshakeMessage hm = (HandshakeMessage) m;
		    clientsMappings.put(hm.getId(), new Client(connection, hm.getId()));
		    Log.info("chatserver", hm.getId() + " has connected");
		    break;
	    }
	}
    }

}
