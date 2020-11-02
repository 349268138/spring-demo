package com.meituan.pay.finsecurity.sdk.api.dto;

import com.facebook.swift.codec.ThriftEnum;
import com.facebook.swift.codec.ThriftEnumValue;

@ThriftEnum
public enum ResStatusEnum {
    SUCCESS(0),
    FAIL(1);

    private final Integer value;

    private ResStatusEnum(Integer value) {
        this.value = value;
    }

    @ThriftEnumValue
    public Integer getValue() {
        return this.value;
    }
}
