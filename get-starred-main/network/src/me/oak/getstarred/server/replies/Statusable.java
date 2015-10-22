package me.oak.getstarred.server.replies;

/**
 *
 * @author White Oak
 */
public interface Statusable {

    public Status getStatus();

    public static enum Status {
	SUCCESS, ERROR, WARNING, INFO, DEBUG
    }
}
