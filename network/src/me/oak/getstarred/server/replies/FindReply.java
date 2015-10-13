package me.oak.getstarred.server.replies;

import lombok.Getter;

/**
 *
 * @author White Oak
 */
@Getter public class FindReply extends Reply implements Statusable {

    private String status;

    @Override
    public Type getType() {
	return Type.FINDING;
    }
}
