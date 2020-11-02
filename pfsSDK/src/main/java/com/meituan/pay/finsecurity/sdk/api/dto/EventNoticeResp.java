package com.meituan.pay.finsecurity.sdk.api.dto;

import com.facebook.swift.codec.ThriftConstructor;
import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftStruct;

import javax.validation.constraints.NotNull;

/**
 * @author hhhb
 * @date 2020/10/28 5:37 下午
 */
@ThriftStruct
public class EventNoticeResp {

    /**
     * 调用结果
     */
    @NotNull(message="调用结果status不能为空")
    private ResStatusEnum status;

    /**
     * 处理结论（1通过,2拦截）
     */
    private Integer resultLevel;

    /**
     * 资金安全受理失败错误码编码
     */
    private String errorCode;

    /**
     * 资金安全受理失败错误码描述信息
     */
    private String errorMsg;

    @ThriftConstructor
    public EventNoticeResp() {
    }

    @ThriftField(1)
    public ResStatusEnum getStatus() {
        return status;
    }

    @ThriftField
    public void setStatus(ResStatusEnum status) {
        this.status = status;
    }

    @ThriftField(2)
    public Integer getResultLevel() {
        return resultLevel;
    }

    @ThriftField
    public void setResultLevel(Integer resultLevel) {
        this.resultLevel = resultLevel;
    }

    @ThriftField(3)
    public String getErrorCode() {
        return errorCode;
    }

    @ThriftField
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @ThriftField(4)
    public String getErrorMsg() {
        return errorMsg;
    }

    @ThriftField
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "EventNoticeResp{" +
                "status=" + status +
                ", resultLevel=" + resultLevel +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
