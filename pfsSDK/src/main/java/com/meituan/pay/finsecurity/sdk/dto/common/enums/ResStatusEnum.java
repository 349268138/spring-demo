package com.meituan.pay.finsecurity.sdk.dto.common.enums;

import com.facebook.swift.codec.ThriftEnum;
import com.facebook.swift.codec.ThriftEnumValue;

@ThriftEnum
public enum ResStatusEnum {

    SUCCESS(1, "成功"),

    FAIL(2, "失败");

    private Integer value;
    private String desc;

    ResStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @ThriftEnumValue
    public Integer getValue() {
        return value;
    }

}