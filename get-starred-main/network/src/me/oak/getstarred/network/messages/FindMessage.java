package me.oak.getstarred.network.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author White Oak
 */
@Getter @RequiredArgsConstructor public class FindMessage extends Message {

    private final String digest;
    private final MessageType type = MessageType.FIND;

}
