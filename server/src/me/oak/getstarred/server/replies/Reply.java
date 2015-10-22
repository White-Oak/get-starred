package me.oak.getstarred.server.replies;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author White Oak
 */
@RequiredArgsConstructor @Getter public abstract class Reply {

    private final String message;

    public static Class getHiddenClassUser() {
	return User.class;
    }

    protected abstract Type getType();

    protected enum Type {

	REGISTER, LOGIN, PLAIN, FINDING, MATCH_READY
    }
}
