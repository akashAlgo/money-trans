package drop.wiz.money.exception;

/**
 * Author: arastogi
 */

public class InvalidAccountTypeException extends Exception {

    public InvalidAccountTypeException() {
        super();
    }

    public InvalidAccountTypeException(String message) {
        super(message);
    }

    public InvalidAccountTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAccountTypeException(Throwable cause) {
        super(cause);
    }

    protected InvalidAccountTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
