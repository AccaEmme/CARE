/**
 * @author giuliano ranauro
 * Date: 13/06/2021 01:24
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.CARE.model.Exceptions;

public class NullPasswordException extends Exception {
    public NullPasswordException() {
        super();
    }

    public NullPasswordException(String m) {
        super(m);
    }
}
