package me.oak.getstarred.server.chat;

import java.util.LinkedList;
import java.util.List;
import me.oak.getstarred.server.chat.messages.ChatMessage;

/**
 *
 * @author White Oak
 */
public class ChatLobby {

    private final int first;
    private final int second;
    private final List<ChatMessage> chatMessages = new LinkedList<>();

    public ChatLobby(int first, int second) {
	this.first = first;
	this.second = second;
    }

    @Override
    public int hashCode() {
	return this.first + this.second;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final ChatLobby other = (ChatLobby) obj;
	return equals(other.first, other.second);
    }

    public boolean equals(int from, int to) {
	return isIn(from) && isIn(to);
    }

    public void add(ChatMessage chatMessage) {
	if (!equals(chatMessage.getFrom(), chatMessage.getTo())) {
	    throw new IllegalArgumentException();
	}
	chatMessages.add(chatMessage);
    }

    public int getOther(int id) {
	return id == first ? second : first;
    }

    public boolean isIn(int id) {
	return id == first || id == second;
    }

    public void add(int to, String message) {
	add(getOther(to), to, message);
    }

    public void add(int from, int to, String message) {
	add(new ChatMessage(from, to, message));
    }
}
