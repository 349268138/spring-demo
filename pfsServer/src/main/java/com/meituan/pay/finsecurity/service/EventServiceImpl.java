package com.meituan.pay.finsecurity.service;

import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.po.ContextData;
import com.meituan.pay.finsecurity.po.TradeEvent;
import com.meituan.pay.finsecurity.po.enums.ProcessResultEnum;
import com.meituan.pay.finsecurity.sdk.api.EventService;
import com.meituan.pay.finsecurity.sdk.dto.req.EventNoticeReq;
import com.meituan.pay.finsecurity.sdk.dto.resp.EventNoticeResp;
import com.meituan.pay.finsecurity.service.data.DataService;
import com.meituan.pay.finsecurity.service.event.EventProcessor;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hhhb
 * @date 2020/10/29 2:44 下午
 */
public class EventServiceImpl implements EventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private EventProcessor eventProcessor;

    @Autowired
    private DataService dataService;

    @Override
    public EventNoticeResp eventNotice(EventNoticeReq req) throws TException {
        EventNoticeResp response = null;
        try {
            String eventCode = req.getEventCode();
            ContextData contextData = buildContext(req);
            TradeEvent tradeEvent = dataService.obtaintradeEvent(eventCode);
            ProcessResultEnum processResultEnum = eventProcessor.process(tradeEvent, contextData);
            response = EventNoticeResp.genSuccessResponse(processResultEnum.getCode());
            LOGGER.info("上游事件数据同步：请求报文：{}, 响应报文：{}", req, response);
        } catch (Exception e) {
            LOGGER.error("上游事件数据同步发生异常：请求报文：{}, 响应报文:{}, 异常:{}", req, response, LoggerUtils.getStackTrace(e));
            response = EventNoticeResp.handleException(e);
        }
        return response;
    }

    private ContextData buildContext(EventNoticeReq req) {
        ContextData contextData = new ContextData();
        String eventData = req.getEventData();
        String tradeData = dataService.obtainTradeData(req.getEventCode(), eventData);
        contextData.setTradeData(tradeData);
        contextData.setEventData(eventData);
        return contextData;
    }
}
