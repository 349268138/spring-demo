package com.meituan.pay.finsecurity.sdk.api.dto;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftStruct;

/**
 * @author hhhb
 * @date 2020/10/28 6:12 下午
 */
@ThriftStruct
public class EventNoticeReq {

    /**
     * 事件标识(fundsRequest, fundsComplete)
     */
    private String eventCode;

    /**
     * 数据
     */
    private String eventData;

    private Boolean repeated;

    private Long eventTime;

    @ThriftField(1)
    public String getEventCode() {
        return eventCode;
    }

    @ThriftField
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    @ThriftField(2)
    public String getEventData() {
        return eventData;
    }

    @ThriftField
    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    @ThriftField(3)
    public Boolean getRepeated() {
        return repeated;
    }

    @ThriftField
    public void setRepeated(Boolean repeated) {
        this.repeated = repeated;
    }

    @ThriftField(4)
    public Long getEventTime() {
        return eventTime;
    }

    @ThriftField
    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "EventNoticeResp{" +
                "status=" + eventCode +
                ", resultLevel=" + eventData +
                ", errorCode='" + repeated + '\'' +
                ", errorMsg='" + eventTime + '\'' +
                '}';
    }
}
