package me.oak.getstarred.server.replies;

import lombok.Data;

/**
 *
 * @author White Oak
 */
@Data public abstract class Reply {

    protected String message;

    public abstract Type getType();

    public final Class<? extends Reply> getAssociatedClass() {
	switch (getType()) {
	    case LOGIN:
		return LoginReply.class;
	    case REGISTER:
		return RegisterReply.class;
	    case PLAIN:
		return PlainReply.class;
	    case FINDING:
		return FindReply.class;
	    case MATCH_READY:
		return ReadyReply.class;
	    default:
		return null;
	}
    }

    public static enum Type {
	REGISTER, LOGIN, PLAIN, FINDING, MATCH_READY
    }
}
