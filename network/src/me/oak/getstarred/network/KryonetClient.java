package me.oak.getstarred.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import lombok.Getter;
import me.oak.getstarred.network.messages.Message;
import me.oak.getstarred.server.replies.Reply;
import me.oak.getstarred.server.replies.ReplyDummy;
import me.whiteoak.minlog.Log;

/**
 *
 * @author White Oak
 */
public class KryonetClient extends Listener {

    private static final Gson GSON = new Gson();
    public Client server = new Client(8192, 1024);
    @Getter private final Queue<Reply> replies = new LinkedList<>();

    public void start() throws IOException {
	Thread thread = new Thread(() -> {
	    server.start();
	    connect();
	    server.addListener(this);
	    register(server.getKryo());
	    Log.info("client", "Chat is connected");
	}, "chat client");
	thread.start();
	try {
	    thread.join();
	} catch (InterruptedException ex) {
	    ex.printStackTrace();
	}
    }

    public void connect() {
	try {
	    server.connect(2000, "localhost", 1234);
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
    }

    private void register(Kryo kryo) {
	kryo.register(String.class);
    }

    @Override
    public void received(Connection connection, Object object) {
	if (object instanceof String) {
	    String s = (String) object;
	    Reply r = GSON.fromJson(s, GSON.fromJson(s, ReplyDummy.class).getAssociatedClass());
	    replies.add(r);
	}
    }

    public void send(Message message) {
	server.sendTCP(GSON.toJson(message));
    }

}
