package com.meituan.pay.finsecurity.po.enums;

/**
 * @author wangjinping
 * @date 2020/10/27
 * @destription
 */
public enum DataAccessTypeEnum {

    RPC(1, "rpc"),
    SQUIRREL(2, "squirrel");

    private int code;
    private String desc;


    DataAccessTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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