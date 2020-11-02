package com.meituan.pay.finsecurity.sdk.dto.common.enums;

import com.facebook.swift.codec.ThriftEnum;
import com.facebook.swift.codec.ThriftEnumValue;

@ThriftEnum
public enum ErrorCodeEnum {

    /**通过注解校验参数返回错误码，错误描述信息由注解校验返回**/
    SYS_ERROR("SYS_ERRPR", "系统异常，请稍候再试", ErrorType.INTERNAL_SYSTEM_ERROR),
    PARAM_ERROR("PARAM_ERROR", "", ErrorType.PARAM_ERROR),
    QUERY_DATA_NOT_EXSIT("QUERY_DATA_NOT_EXSIT", "查询数据不存在", ErrorType.BIZ_ERROR);

    private String code;
    private String desc;
    private ErrorType errorType;

    ErrorCodeEnum(String code, String desc, ErrorType errorType) {
        this.code = code;
        this.desc = desc;
        this.errorType = errorType;
    }

    @ThriftEnumValue
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ThriftEnumValue
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @ThriftEnumValue
    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public enum ErrorType {
        /**
         * 参数异常
         */
        PARAM_ERROR,
        /**
         * 业务异常
         */
        BIZ_ERROR,
        /**
         * 依赖系统异常
         */
        DEPEND_SYSTEM_ERROR,
        /**
         * 内部异常
         */
        INTERNAL_SYSTEM_ERROR,
    }

    @Override
    public String toString() {
        return "ErrorCodeEnum{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                ", errorType=" + errorType +
                '}';
    }
}
