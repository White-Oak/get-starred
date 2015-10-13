package me.oak.getstarred.server.chat.messages;

/**
 *
 * @author White Oak
 */
public abstract class Message {

    public abstract Type getType();

    public static enum Type {
	HANDSHAKE, CHAT_WITH, MESSAGE
    }
}
