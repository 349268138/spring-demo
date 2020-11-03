package com.meituan.pay.finsecurity.sdk.api;

import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;
import com.meituan.pay.finsecurity.sdk.dto.req.EventNoticeReq;
import com.meituan.pay.finsecurity.sdk.dto.resp.EventNoticeResp;
import org.apache.thrift.TException;

/**
 * @author hhhb
 * @date 2020/10/28 5:36 下午
 */
@ThriftService
public interface EventNotice {

    @ThriftMethod
    EventNoticeResp eventNotice(EventNoticeReq req) throws TException;
}
