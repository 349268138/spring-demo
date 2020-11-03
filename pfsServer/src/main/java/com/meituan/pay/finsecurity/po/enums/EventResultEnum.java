package com.meituan.pay.finsecurity.po.enums;

/**
 * Created by zhoutianji on 18/6/20.
 */
public enum EventResultEnum implements CodeEnum{
    PASS(1),
    INTERCEPT(2);

    private int code;

    EventResultEnum(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    public static EventResultEnum findValue(int code) {
        for (EventResultEnum type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }

        return null;
    }
}
