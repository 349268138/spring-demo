
package com.meituan.pay.finsecurity.sdk.dto.resp;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftStruct;
import com.meituan.pay.finsecurity.sdk.dto.common.enums.ErrorCodeEnum;
import com.meituan.pay.finsecurity.sdk.dto.common.enums.ResStatusEnum;
import com.meituan.pay.finsecurity.sdk.dto.exception.CustomException;
import org.apache.thrift.TException;


@ThriftStruct
public class EventNoticeResp {

    /**
     * 调用结果
     */
    private ResStatusEnum status;

    /**
     * 处理结论（1通过,2拦截）
     */
    private Integer resultLevel;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误码信息
     */
    private String errorMsg;

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

    /**
     * 返回成功结果
     *
     * @param resultLevel
     * @return
     */
    public static EventNoticeResp genSuccessResponse(Integer resultLevel) {
        EventNoticeResp response = new EventNoticeResp();
        response.setStatus(ResStatusEnum.SUCCESS);
        response.setResultLevel(resultLevel);
        return response;
    }

    /**
     * 返回失败结果
     *
     * @param errorCode
     * @param errorMsg
     * @return
     */
    public static EventNoticeResp genFailResponse(String errorCode, String errorMsg) {
        EventNoticeResp response = new EventNoticeResp();
        response.setStatus(ResStatusEnum.FAIL);
        response.setErrorCode(errorCode);
        response.setErrorMsg(errorMsg);
        return response;
    }

    /**
     * 处理系统异常
     *
     * @param e
     * @return
     * @throws TException
     */
    public static EventNoticeResp handleException(Exception e) throws TException {
        if (e instanceof CustomException) {
            return handleException((CustomException) e);
        } else if (e.getCause() instanceof CustomException) {
            return handleException((CustomException) e.getCause());
        } else {
            throw new TException(e);
        }
    }

    /**
     * 处理自定义错误码异常
     *
     * @param ex
     * @return
     */
    public static EventNoticeResp handleException(CustomException ex) throws TException {

        ErrorCodeEnum.ErrorType errorType = ex.getErrorCodeEnum().getErrorType();
        //参数错误或业务逻辑明确失败的，返回上游失败错误码和信息
        if (errorType == ErrorCodeEnum.ErrorType.PARAM_ERROR || errorType == ErrorCodeEnum.ErrorType.BIZ_ERROR) {
            return genFailResponse(String.valueOf(ex.getErrorCodeEnum().getCode()), ex.getMessage());
        }

        //依赖下游异常或内部异常的，抛出TException传递异常信息，由上游发起重试
        throw new TException(ex.getErrorCodeEnum().getDesc(), ex);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TradeDataResp{");
        sb.append("status=").append(status);
        sb.append(", resultLevel=").append(resultLevel);
        sb.append(", errorCode='").append(errorCode).append('\'');
        sb.append(", errorMsg='").append(errorMsg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}