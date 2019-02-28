package top.hihuzi.collection.exception;

/**
 * tips 异常
 *
 * @author: hihuzi 2019/2/19 8:41
 */
public class NoticeException extends Exception {

    public NoticeException() {

        super();
    }


    public NoticeException(String message) {

        super(message);
    }


    public NoticeException(String message, Throwable cause) {

        super(message, cause);
    }


    public NoticeException(Throwable cause) {

        super(cause);
    }


    protected NoticeException(String message, Throwable cause,
                              boolean enableSuppression,
                              boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }


}
