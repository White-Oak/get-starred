package me.oak.getstarred;

import com.esotericsoftware.kryonet.*;
import java.io.IOException;
import java.net.Inet4Address;
import me.oak.getstarred.server.chat.messages.*;
import me.whiteoak.minlog.Log;

/**
 *
 * @author White Oak
 */
public class ChatClient extends Listener {

    private final Client client = new Client(4096, 1024);

    public void connect() throws IOException {
	client.start();
	client.connect(5000, Inet4Address.getLocalHost(), 51446, 51447);
	client.addListener(this);
	registerAll();
	Log.info("client", "Chat is connected");
    }

    private void registerAll() {
	client.getKryo().register(HandshakeMessage.class);
	client.getKryo().register(ChatWithMessage.class);
	client.getKryo().register(MessageMessage.class);
	client.getKryo().register(ChatMessage.class);
    }

    @Override
    public void received(Connection connection, Object object) {
	super.received(connection, object);
	if (object instanceof MessageMessage) {
	    MessageMessage mm = (MessageMessage) object;
	    Log.info("client", "Message is " + mm.getChatMessage());
	}
    }

    public void handshake(int id) {
	Log.info("client", "Handshaking with a chat server");
	client.sendTCP(new HandshakeMessage(id));
	message(id, id, "hi!");
    }

    public void wantToChatWith(int id) {
	Log.info("client", "Sending a request to chat with " + id);
	client.sendTCP(new ChatWithMessage(id));
    }

    public void message(int from, int to, String message) {
	Log.info("client", "Sending a message to  " + to);
	client.sendUDP(new MessageMessage(new ChatMessage(from, to, message)));
    }
}
