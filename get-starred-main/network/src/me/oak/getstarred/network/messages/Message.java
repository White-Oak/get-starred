package me.oak.getstarred.network.messages;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor public abstract class Message {

    public abstract MessageType getType();

}
