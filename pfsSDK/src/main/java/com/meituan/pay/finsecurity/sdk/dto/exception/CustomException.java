package com.meituan.pay.finsecurity.sdk.dto.exception;

import com.meituan.pay.finsecurity.sdk.dto.common.enums.ErrorCodeEnum;

/**
 * @author wangjinping
 * @date 2020/10/26
 * @destription 错误码异常处理类
 */
public class CustomException extends RuntimeException {
    private ErrorCodeEnum errorCodeEnum;

    public CustomException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getDesc());
        this.errorCodeEnum = errorCodeEnum;
    }

    public CustomException(ErrorCodeEnum errorCodeEnum, String subMsg) {
        super(subMsg);
        this.errorCodeEnum = errorCodeEnum;
    }


    public CustomException(ErrorCodeEnum errorCodeEnum, Throwable cause) {
        super(errorCodeEnum.getDesc(), cause);
        this.errorCodeEnum = errorCodeEnum;
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

}
