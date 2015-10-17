package me.oak.getstarred.server.replies;

import lombok.Data;

/**
 *
 * @author White Oak
 */
@Data public abstract class Reply {

    protected String message;

    public abstract Type getType();

    public static enum Type {
	REGISTER, LOGIN, PLAIN, FINDING, MATCH_READY
    }
}
