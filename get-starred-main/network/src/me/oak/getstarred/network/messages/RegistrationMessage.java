package me.oak.getstarred.network.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author White Oak
 */
@Getter @RequiredArgsConstructor public class RegistrationMessage extends Message {

    private final String login;
    private final String password_digest;

    private final MessageType type = MessageType.REGISTRATION_REQ;

}
