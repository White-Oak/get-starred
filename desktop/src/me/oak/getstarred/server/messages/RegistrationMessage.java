package me.oak.getstarred.server.messages;

import lombok.Getter;
import me.oak.getstarred.server.ServerContext;

/**
 *
 * @author White Oak
 */
@Getter public class RegistrationMessage extends Message {

    private final String login;
    private final String password_digest;

    public RegistrationMessage(String login, String password_digest) {
	super(MessageType.REGISTRATION_REQ);
	this.login = login;
	this.password_digest = password_digest;
    }

    @Override
    public void process(ServerContext context, int cID) {

//        RegistrationReplyMessage registrationReplyMessage
//                                 = context.getAccountManager().tryRegister(login, password_digest);
//	context.getNetwork().send(ServerNetwork.GSON.toJson(registrationReplyMessage), cID);
    }

}
