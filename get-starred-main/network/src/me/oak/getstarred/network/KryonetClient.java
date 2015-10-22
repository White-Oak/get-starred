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
    private final static String POOL[] = {"192.168.0.17", "77.244.77.10"};
    public Client server = new Client(8192, 1024);
    @Getter private final Queue<Reply> replies = new LinkedList<>();

    public void start() throws IOException {
	Thread thread = new Thread(() -> {
	    server.start();
	    connectPool(0);
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

    private boolean connectPool(int trial) {
	try {
	    server.connect(5000, POOL[trial], 1234);
	    return true;
	} catch (IOException ex) {
	    trial++;
	    if (trial < POOL.length) {
		return connectPool(trial);
	    } else {
		Log.error("client", "Cannot connect to chat server");
		throw new RuntimeException("Cannot connect to the main server");
	    }
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
