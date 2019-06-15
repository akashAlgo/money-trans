package drop.wiz.money.exception;

/**
 * Author: arastogi
 * Date: 2019-06-15
 */
public class TransactionFailedException extends Exception {
    public TransactionFailedException() {
        super();
    }

    public TransactionFailedException(String message) {
        super(message);
    }

    public TransactionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionFailedException(Throwable cause) {
        super(cause);
    }

    protected TransactionFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
