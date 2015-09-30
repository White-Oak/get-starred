package me.oak.getstarred.network.messages;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.network.ClientNetwork;
import me.oak.getstarred.server.replies.Reply;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public abstract class Message {

    public static final Gson GSON = new Gson();
    @Getter private final MessageType type;

    public abstract Reply process(ClientNetwork network);
}
