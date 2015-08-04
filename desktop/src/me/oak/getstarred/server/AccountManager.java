package me.oak.getstarred.server;

import me.oak.getstarred.server.messages.replies.LoginReplyMessage;
import me.oak.getstarred.server.messages.replies.RegistrationReplyMessage;
import me.whiteoak.minlog.Log;

/**
 *
 * @author oak
 */
public class AccountManager {

    public RegistrationReplyMessage tryRegister(String login, String password_digest) {
        Log.info("server", login + " is registered");
        return new RegistrationReplyMessage("success", login + " was registered");
    }

    public LoginReplyMessage tryLogin(String login, String password_digest) {
        Log.info("server", login + " is logged in");
        return new LoginReplyMessage("success", login + " was logged in.", "sample");
    }

}
