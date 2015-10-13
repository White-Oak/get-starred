package me.oak.getstarred.server.replies;

import lombok.ToString;

/**
 *
 * @author White Oak
 */
@ToString(callSuper = true)
public class PlainReply extends Reply {

    @Override
    public Reply.Type getType() {
	return Reply.Type.PLAIN;
    }
}
