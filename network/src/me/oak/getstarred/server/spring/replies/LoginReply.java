package me.oak.getstarred.server.spring.replies;

import lombok.Value;

/**
 *
 * @author White Oak
 */
@Value public class LoginReply {

    private final String status;
    private final String message;
    private final String digest;
}
