package me.oak.getstarred.server.replies;

import lombok.Value;

/**
 *
 * @author White Oak
 */
@Value public class ReadyReply {

    private Status status;
    private String message;
}
