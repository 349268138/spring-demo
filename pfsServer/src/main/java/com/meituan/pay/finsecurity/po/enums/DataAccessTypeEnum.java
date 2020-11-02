package com.meituan.pay.finsecurity.po.enums;

/**
 * @author wangjinping
 * @date 2020/10/27
 * @destription
 */
public enum DataAccessTypeEnum implements CodeEnum{

    RPC(1),
    SQUIRREL(2);

    private int code;

    DataAccessTypeEnum(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static DataAccessTypeEnum findValue(int code) {
        for (DataAccessTypeEnum type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }

        return null;
    }
}