package me.oak.getstarred.server.replies;

import lombok.Getter;
import lombok.ToString;
import me.oak.getstarred.network.User;

/**
 *
 * @author White Oak
 */
@Getter @ToString(callSuper = true) public class FindReply extends Reply implements Statusable {

    private Status status;
    private User user;

    @Override
    public Type getType() {
	return Type.FINDING;
    }
}
