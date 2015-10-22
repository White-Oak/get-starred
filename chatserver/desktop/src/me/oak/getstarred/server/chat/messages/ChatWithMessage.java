package me.oak.getstarred.server.chat.messages;

import lombok.*;

/**
 *
 * @author White Oak
 */
@AllArgsConstructor @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED) public class ChatWithMessage extends Message {

    private int withId;

    @Override
    public Type getType() {
	return Type.CHAT_WITH;
    }

}
