package me.oak.getstarred.server.messages;

import lombok.Getter;
import me.oak.getstarred.server.ServerContext;
import me.oak.getstarred.server.ServerNetwork;
import me.oak.getstarred.server.messages.replies.LoginReplyMessage;

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
        LoginReplyMessage loginReplyMessage
                          = context.getAccountManager().tryLogin(login, password_digest);
        context.getNetwork().send(ServerNetwork.GSON.toJson(loginReplyMessage), cID);
    }

}
