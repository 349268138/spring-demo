package com.meituan.pay.finsecurity.service;

import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.sdk.api.EventNotice;
import com.meituan.pay.finsecurity.sdk.api.dto.EventNoticeReq;
import com.meituan.pay.finsecurity.sdk.api.dto.EventNoticeResp;
import com.meituan.pay.finsecurity.sdk.api.dto.ResStatusEnum;
import com.meituan.pay.finsecurity.service.event.EventProcessor;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hhhb
 * @date 2020/10/29 2:44 下午
 */
public class EventNoticeImpl implements EventNotice {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventNoticeImpl.class);

    @Autowired
    private EventProcessor eventProcessor;

    @Override
    public EventNoticeResp eventNotice(EventNoticeReq req) throws TException {
        LOGGER.info("事件数据同步：请求报文：{}", req.toString());
        EventNoticeResp response = null;
        try {
            System.out.println(req.getEventData());
            response = new EventNoticeResp();
            response.setStatus(ResStatusEnum.SUCCESS);
            response.setResultLevel(1);
            LOGGER.info("事件数据同步：响应报文：{}", response.toString());
            return response;
        } catch (Exception e) {
            response.setStatus(ResStatusEnum.FAIL);
            response.setErrorCode("errcode");
            response.setErrorMsg("errmsg");
            LOGGER.info("事件数据同步发生异常：响应报文:{}, 异常:{}", response.toString(), LoggerUtils.getStackTrace(e));
            return response;
        }
    }
}
