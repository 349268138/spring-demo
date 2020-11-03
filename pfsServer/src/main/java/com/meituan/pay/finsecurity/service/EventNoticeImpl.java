package com.meituan.pay.finsecurity.service;

import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.po.ContextData;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.TradeEvent;
import com.meituan.pay.finsecurity.po.enums.ProcessResultEnum;
import com.meituan.pay.finsecurity.sdk.api.EventNotice;
import com.meituan.pay.finsecurity.sdk.dto.common.enums.ResStatusEnum;
import com.meituan.pay.finsecurity.sdk.dto.req.EventNoticeReq;
import com.meituan.pay.finsecurity.sdk.dto.resp.EventNoticeResp;
import com.meituan.pay.finsecurity.service.data.DataService;
import com.meituan.pay.finsecurity.sdk.dto.*;
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

    @Autowired
    private DataService dataService;


    @Override
    public EventNoticeResp eventNotice(EventNoticeReq req) throws TException {
        LOGGER.info("事件数据同步：请求报文：{}", req.toString());
        EventNoticeResp response = null;
        try {
            String eventCode = req.getEventCode();
            ContextData contextData = buildContext(req, eventCode);
            TradeEvent tradeEvent = dataService.obtaintradeEvent(eventCode);
            ProcessResultEnum processResultEnum = eventProcessor.process(tradeEvent, contextData);
            response = EventNoticeResp.genSuccessResponse(processResultEnum.getCode());
            LOGGER.info("事件数据同步：响应报文：{}", response.toString());
        } catch (Exception e) {
            response = EventNoticeResp.handleException(e);
            LOGGER.info("事件数据同步发生异常：响应报文:{}, 异常:{}", response.toString(), LoggerUtils.getStackTrace(e));
        }
        return response;
    }

    private ContextData buildContext(EventNoticeReq req, String eventCode) {
        ContextData contextData = new ContextData();
        String eventData = req.getEventData();
        String tradeData = dataService.obtainTradeData(req.getEventCode(), eventData);
        contextData.setTradeData(tradeData);
        contextData.setEventData(eventData);
        return contextData;
    }
}
