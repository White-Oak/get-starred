package me.oak.getstarred.network;

import lombok.Value;

/**
 *
 * @author White Oak
 */
@Value public class Result<T> {

    private String message;
    private T reply;
}
