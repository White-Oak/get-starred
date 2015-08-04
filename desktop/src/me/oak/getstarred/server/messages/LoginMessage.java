package me.oak.getstarred.server.messages;

import me.oak.getstarred.server.messages.replies.LoginReplyMessage;
import lombok.Getter;
import me.oak.getstarred.server.ServerContext;
import me.oak.getstarred.server.ServerNetwork;
import me.whiteoak.minlog.Log;

/**
 *
 * @author White Oak
 */
@Getter
public class LoginMessage extends Message {

    private final String login;
    private final String password_digest;

    public LoginMessage(String login, String password_digest) {
        super(MessageType.LOGIN_REQ);
        this.login = login;
        this.password_digest = password_digest;
    }

    @Override
    public void process(ServerContext context, int cID) {
        LoginReplyMessage loginReplyMessage = new LoginReplyMessage("success", login + " was logged in.",
                                                                    "sample");
        context.getNetwork().send(ServerNetwork.GSON.toJson(loginReplyMessage), cID);
        Log.info("server", login + " is logged in");
    }

}
