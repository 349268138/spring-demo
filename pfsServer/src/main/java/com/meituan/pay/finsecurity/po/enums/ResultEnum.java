package com.meituan.pay.finsecurity.po.enums;

/**
 * Created by zhoutianji on 18/6/20.
 */
public enum ResultEnum implements CodeEnum{
    PASS(1),
    INTERCEPT(2);

    private int code;

    ResultEnum(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    public static ResultEnum findValue(int code) {
        for (ResultEnum type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }

        return null;
    }
}
