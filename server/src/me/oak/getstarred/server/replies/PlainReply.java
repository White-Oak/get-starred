package me.oak.getstarred.server.replies;

import lombok.Getter;

public class PlainReply extends Reply {

    @Getter protected final Type type = Type.PLAIN;
    private Status status;

    public PlainReply(Status status, String message) {
	super(message);
	this.status = status;
    }

}
