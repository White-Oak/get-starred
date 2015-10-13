package me.oak.getstarred.server.replies;

import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author White Oak
 */
@ToString(callSuper = true)
@Getter public class LoginReply extends Reply implements Statusable {

    private Status status;
    private String digest;

    @Override
    public Type getType() {
	return Type.LOGIN;
    }

}
