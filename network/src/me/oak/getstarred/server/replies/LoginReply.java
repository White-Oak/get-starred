package me.oak.getstarred.server.replies;

import lombok.Getter;
import lombok.ToString;
import me.oak.getstarred.network.User;

/**
 *
 * @author White Oak
 */
@ToString(callSuper = true)
@Getter public class LoginReply extends Reply implements Statusable {

    private Status status;
    private User user;
    private String digest;

    @Override
    public Type getType() {
	return Type.LOGIN;
    }
}
