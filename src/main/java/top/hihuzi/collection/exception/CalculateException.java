package top.hihuzi.collection.exception;

/**
 * tips 字符转数字异常
 *
 * @author: hihuzi  2019/4/25 19:46
 */
public class CalculateException extends RuntimeException {

    public CalculateException() {

        super();
    }

    public CalculateException(String message) {

        super(message);
    }

    public CalculateException(String message, Throwable cause) {

        super(message, cause);
    }

    public CalculateException(Throwable cause) {

        super(cause);
    }

    protected CalculateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }

}
