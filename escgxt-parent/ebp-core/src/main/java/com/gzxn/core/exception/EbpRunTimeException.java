package com.gzxn.core.exception;

/**
 * @Package: com.gzxn.core.exception
 * @ClassName: EbpRunTimeException
 * @Author: CodeBird
 * @Date: 2022-03-06 12:26
 * @Description: Ebp运行时异常
 */
public class EbpRunTimeException extends RuntimeException {

    public EbpRunTimeException() {
    }

    public EbpRunTimeException(String message) {
        super(message);
    }

    public EbpRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EbpRunTimeException(Throwable cause) {
        super(cause);
    }

    public EbpRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
