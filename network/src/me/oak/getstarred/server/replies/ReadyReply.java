package me.oak.getstarred.server.replies;

import lombok.Getter;

/**
 *
 * @author White Oak
 */
@Getter public class ReadyReply extends StatusReply {

    private final Type type = Type.MATCH_READY;

}
