package top.hihuzi.collection.exception;

/**
 * <p> 异常
 *
 * @author hihuzi 2019/2/19 8:41
 */
public class NoticeException extends RuntimeException {

    /**
     * Instantiates a new Notice exception.
     */
    public NoticeException() {

        super();
    }


    /**
     * Instantiates a new Notice exception.
     *
     * @param message the message
     */
    public NoticeException(String message) {

        super(message);
    }


    /**
     * Instantiates a new Notice exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public NoticeException(String message, Throwable cause) {

        super(message, cause);
    }


    /**
     * Instantiates a new Notice exception.
     *
     * @param cause the cause
     */
    public NoticeException(Throwable cause) {

        super(cause);
    }


    /**
     * Instantiates a new Notice exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected NoticeException(String message, Throwable cause,
                              boolean enableSuppression,
                              boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }


}
