package com.meituan.pay.finsecurity.po.enums;


public enum StatusEnum implements CodeEnum{
    ON(1),
    OFF(2);

    private int code;

    StatusEnum(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    public static StatusEnum findValue(int code) {
        for (StatusEnum type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }

        return null;
    }
}
