package com.meituan.pay.finsecurity.po.enums;


public enum ProcessResultEnum implements CodeEnum{
    PASS(1),
    INTERCEPT(2);

    private int code;

    ProcessResultEnum(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    public static ProcessResultEnum findValue(int code) {
        for (ProcessResultEnum type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }

        return null;
    }
}
