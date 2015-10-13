package me.oak.getstarred.server.replies;

import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author White Oak
 */
@ToString(callSuper = true)
@Getter public class RegisterReply extends Reply implements Statusable {

    private Status status;

    @Override
    public Type getType() {
	return Type.REGISTER;
    }
}
