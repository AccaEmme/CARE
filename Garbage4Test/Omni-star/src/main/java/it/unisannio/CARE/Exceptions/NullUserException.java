/**
 * @author giuliano ranauro
 * Date: 13/06/2021 01:23
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.CARE.Exceptions;

public class NullUserException extends Exception {
    public NullUserException() {
        super();
    }

    public NullUserException(String m) {
        super(m);
    }
}
