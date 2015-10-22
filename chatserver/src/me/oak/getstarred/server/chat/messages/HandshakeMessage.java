package me.oak.getstarred.server.chat.messages;

import lombok.*;

/**
 *
 * @author White Oak
 */
@AllArgsConstructor @Getter @NoArgsConstructor public class HandshakeMessage extends Message {

    private int id;

    @Override
    public Type getType() {
	return Type.HANDSHAKE;
    }

}
