package me.oak.getstarred.server.replies;

import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author White Oak
 */
@Getter @ToString(callSuper = true) public class FindReply extends Reply implements Statusable {

    private Status status;
    private int userId;

    @Override
    public Type getType() {
	return Type.FINDING;
    }
}
