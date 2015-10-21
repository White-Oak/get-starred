package me.oak.getstarred.server.replies;

import lombok.Getter;

public class PlainReply extends Reply {

    @Getter protected final Type type = Type.PLAIN;

    public PlainReply(String message) {
	super(message);
    }

}
