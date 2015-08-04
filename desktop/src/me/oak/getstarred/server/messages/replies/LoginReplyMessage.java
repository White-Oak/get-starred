package me.oak.getstarred.server.messages.replies;

import lombok.Getter;
import me.oak.getstarred.server.messages.Message;
import me.oak.getstarred.server.messages.MessageType;

/**
 *
 * @author White Oak
 */
@Getter
public class LoginReplyMessage extends Message {

    private final String status;
    private final String message;
    private final String playerid;

    public LoginReplyMessage(String status, String message, String playerid) {
        super(MessageType.LOGIN_REQ_REPLY);
        this.status = status;
        this.message = message;
        this.playerid = playerid;
    }

}
