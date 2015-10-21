package me.oak.getstarred.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.concurrent.*;
import lombok.AllArgsConstructor;
import me.oak.getstarred.server.messages.*;
import me.oak.getstarred.server.replies.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author White Oak
 */
@Service
public class KryonetServer extends Listener {

    private final static Gson GSON = new Gson();

    @Autowired private Managers managers;
    public Server server = new Server(8192, 1024);
    private final BlockingQueue<Bundle> messages = new LinkedBlockingQueue<>();

    public void start() throws IOException {
	server.bind(1234);
	server.addListener(this);
	register(server.getKryo());
	server.start();
    }

    private void register(Kryo kryo) {
	kryo.register(String.class);
    }

    public void processMessages() {
	Bundle poll = null;
	try {
	    poll = messages.poll(2, TimeUnit.DAYS);
	} catch (InterruptedException ex) {
	    ex.printStackTrace();
	}
	poll.message.process(managers, poll.connection);
    }

    @Override
    public void received(Connection connection, Object object) {
	if (object instanceof String) {
	    final String name = (String) object;
	    Message m = GSON.fromJson(name, GSON.fromJson(name, MessageDummy.class).getAssociatedClass());
	    messages.add(new Bundle(m, connection));
	}
    }

    public void send(Reply reply, Connection user) {
	server.sendToTCP(user.getID(), GSON.toJson(reply));
    }

    @AllArgsConstructor private class Bundle {

	private final Message message;
	private final Connection connection;
    }
}
