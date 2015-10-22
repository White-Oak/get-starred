package me.oak.getstarred.server.chat.messages;

import lombok.*;

/**
 *
 * @author White Oak
 */
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED) @Getter public class ChatMessage {

    private int from;
    private int to;
    private String string;

    @Override
    public String toString() {
	return "[" + from + "->" + to + "]: " + string;
    }

}
