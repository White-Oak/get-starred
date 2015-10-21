package me.oak.getstarred.server.replies;

import lombok.Getter;

/**
 *
 * @author White Oak
 */
public abstract class StatusReply extends Reply implements Statusable {

    @Getter private Status status;

    public static Class getHiddenClass() {
	return Status.class;
    }
}
