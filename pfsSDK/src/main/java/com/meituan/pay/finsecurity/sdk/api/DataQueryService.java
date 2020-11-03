package com.meituan.pay.finsecurity.sdk.api;

import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;
import com.meituan.pay.finsecurity.sdk.dto.resp.TradeDataResp;
import org.apache.thrift.TException;

@ThriftService
public interface DataQueryService {

    @ThriftMethod
    TradeDataResp queryTradeData(String key) throws TException;
}
