package me.riseremi.mreader;

/**
 *
 * @author riseremi <riseremi at icloud.com>
 */
public class WrongFormatException extends RuntimeException {

    public WrongFormatException() {
	super("Wrong input data. Please, make sure that input data is properly formatted.");
    }

}
