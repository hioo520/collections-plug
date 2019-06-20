package top.hihuzi.collection.exception;

/**
 * tips 字符转数字异常
 *
 * @author: hihuzi 2019/4/25 19:46
 */
public class CalculateException extends RuntimeException {

    /**
     * Instantiates a new Calculate exception.
     */
    public CalculateException() {

        super();
    }

    /**
     * Instantiates a new Calculate exception.
     *
     * @param message the message
     */
    public CalculateException(String message) {

        super(message);
    }

    /**
     * Instantiates a new Calculate exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public CalculateException(String message, Throwable cause) {

        super(message, cause);
    }

    /**
     * Instantiates a new Calculate exception.
     *
     * @param cause the cause
     */
    public CalculateException(Throwable cause) {

        super(cause);
    }

    /**
     * Instantiates a new Calculate exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected CalculateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }

}
