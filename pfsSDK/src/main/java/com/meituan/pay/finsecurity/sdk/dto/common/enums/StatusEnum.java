package com.meituan.pay.finsecurity.sdk.dto.common.enums;

import com.facebook.swift.codec.ThriftEnum;
import com.facebook.swift.codec.ThriftEnumValue;

/**
 * @Author wangjinping
 * @ Date 2019/11/06 下午8:58
 * @ Description 转账到余额状态
 */
@ThriftEnum
public enum StatusEnum {

    SUCCESS(1, "成功"),

    FAIL(2, "失败");

    private Integer value;
    private String desc;

    StatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @ThriftEnumValue
    public Integer getValue() {
        return value;
    }

}