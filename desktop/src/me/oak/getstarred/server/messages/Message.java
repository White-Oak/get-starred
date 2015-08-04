package me.oak.getstarred.server.messages;

import me.oak.getstarred.server.messages.replies.RegistrationReplyMessage;
import me.oak.getstarred.server.messages.replies.LoginReplyMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.oak.getstarred.server.ServerContext;
import me.oak.getstarred.server.ServerNetwork;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public abstract class Message {

    @Getter private final MessageType type;

    public static Message getMessage(String json, MessageType type) {
        Class clas = getClass(type);
        return (Message) ServerNetwork.GSON.fromJson(json, clas);
    }

    private static Class getClass(MessageType type1) {
        switch (type1) {
            case REGISTRATION_REQ:
                return RegistrationMessage.class;
            case REGISTRATION_REQ_REPLY:
                return RegistrationReplyMessage.class;
            case LOGIN_REQ:
                return LoginMessage.class;
            case LOGIN_REQ_REPLY:
                return LoginReplyMessage.class;
            default:
                return Message.class;
        }
    }

    public void process(ServerContext context, int cID) {
    }
}
