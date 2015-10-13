package me.oak.getstarred.server.replies;

import lombok.Getter;

/**
 *
 * @author White Oak
 */
@Getter public class FindReply extends Reply implements Statusable {

    private Status status;

    @Override
    public Type getType() {
	return Type.FINDING;
    }
}
