package com.meituan.pay.finsecurity.sdk.api;

import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;
import org.apache.thrift.TException;

/**
 * @author zhanghuanqi
 * @date 2020/6/3
 * @destription
 */
@ThriftService
public interface DataQueryService {

    @ThriftMethod
    String queryTradeData(String key) throws TException;
}
