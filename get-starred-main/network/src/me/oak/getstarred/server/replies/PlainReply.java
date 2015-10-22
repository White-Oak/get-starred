package me.oak.getstarred.server.replies;

import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author White Oak
 */
@ToString(callSuper = true)
public class PlainReply extends Reply {

    @Getter private final Type type = Type.PLAIN;
}
