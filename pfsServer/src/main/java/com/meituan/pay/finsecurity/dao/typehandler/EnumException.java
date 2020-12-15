package com.meituan.pay.finsecurity.dao.typehandler;

/**
 * Created by wangjinping on 20/12/11.
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