package me.oak.getstarred;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;
import java.net.Inet4Address;
import me.oak.getstarred.server.chat.messages.HandshakeMessage;
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
    }

    public void handshake(int id) {
	Log.info("client", "Handshaking with a chat server");
	client.sendUDP(new HandshakeMessage(id));
    }
}
