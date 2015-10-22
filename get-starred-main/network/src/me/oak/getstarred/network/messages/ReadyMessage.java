package me.oak.getstarred.network.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public class ReadyMessage extends Message {

    private final String digest;

    @Getter private final MessageType type = MessageType.READY;

}
