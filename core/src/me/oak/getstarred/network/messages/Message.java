package me.oak.getstarred.network.messages;

import com.google.gson.Gson;
import lombok.*;
import me.oak.getstarred.ClientContext;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public abstract class Message {

    public static final Gson GSON = new Gson();
    @Getter private final MessageType type;

    public static Message getMessage(String json, MessageType type) {
	Class clas = Message.class;
	switch (type) {
	    case REGISTRATION_REQ:
		clas = RegistrationMessage.class;
		break;
	    case REGISTRATION_REQ_REPLY:
		clas = RegistrationReplyMessage.class;
		break;
	}
	return (Message) GSON.fromJson(json, clas);
    }

    public void process(ClientContext context) {
    }
}
