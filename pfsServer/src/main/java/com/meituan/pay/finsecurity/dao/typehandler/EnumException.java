package com.meituan.pay.finsecurity.dao.typehandler;

/**
 * Created by Dreampie on 16/2/4.
 */
public class EnumException extends RuntimeException {
    public EnumException() {
        super();
    }

    public EnumException(String message) {
        super(message);
    }

    public EnumException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnumException(Throwable cause) {
        super(cause);
    }

    protected EnumException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}