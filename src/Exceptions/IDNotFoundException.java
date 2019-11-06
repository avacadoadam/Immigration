package Exceptions;

/**
 * A exception that will be throw when a person ID is not found in the list.
 */
public class IDNotFoundException extends Exception {


    public IDNotFoundException() {
        super("ID was not found in system");
    }
}
