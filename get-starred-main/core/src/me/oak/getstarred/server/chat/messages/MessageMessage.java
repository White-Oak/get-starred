package me.oak.getstarred.server.chat.messages;

import lombok.*;

/**
 *
 * @author White Oak
 */
@AllArgsConstructor @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED) @ToString public class MessageMessage extends Message {

    private ChatMessage chatMessage;

    @Override
    public Message.Type getType() {
	return Message.Type.MESSAGE;
    }

}
