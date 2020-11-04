package com.meituan.pay.finsecurity.po.enums;


public enum TypeEnum implements CodeEnum{
    ALARM(1),
    MONITOR(2),
    INTERCEPT(3),
    FACTOR(4);

    private int code;

    TypeEnum(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    public static TypeEnum findValue(int code) {
        for (TypeEnum type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }

        return null;
    }
}
