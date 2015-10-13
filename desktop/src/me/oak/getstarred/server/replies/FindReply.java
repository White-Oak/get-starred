package me.oak.getstarred.server.replies;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 *
 * @author White Oak
 */
@Value @AllArgsConstructor public class FindReply {

    private final String status;
    private final String message;
}
