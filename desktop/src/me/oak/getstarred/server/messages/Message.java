package me.oak.getstarred.server.messages;

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
            case LOGIN_REQ:
                return LoginMessage.class;
            case DECKS_REQ:
                return DecksRequestMessage.class;
            case FINDING_MATCH:
                return FindingMatchMessage.class;
            case NEW_DECK:
                return NewDeckMessage.class;
            case READY_TO_FIGHT:
                return ReadyTFMessage.class;
            default:
                return Message.class;
        }
    }

    public void process(ServerContext context, int cID) {
    }
}
