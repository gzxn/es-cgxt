package com.gzxn.core.exception;

/**
 * @Package: com.gzxn.core.exception
 * @ClassName: EbpException
 * @Author: CodeBird
 * @Date: 2022-03-06 12:24
 * @Description: Ebp异常
 */
public class EbpException extends Exception {

    public EbpException() {
    }

    public EbpException(String message) {
        super(message);
    }

    public EbpException(String message, Throwable cause) {
        super(message, cause);
    }

    public EbpException(Throwable cause) {
        super(cause);
    }

    public EbpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
