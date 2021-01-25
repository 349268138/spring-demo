package com.meituan.pay.finsecurity.service;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.po.*;
import com.meituan.pay.finsecurity.po.enums.ProcessResultEnum;
import com.meituan.pay.finsecurity.sdk.api.EventService;
import com.meituan.pay.finsecurity.sdk.dto.req.EventNoticeReq;
import com.meituan.pay.finsecurity.sdk.dto.resp.EventNoticeResp;
import com.meituan.pay.finsecurity.service.data.DataService;
import com.meituan.pay.finsecurity.service.decision.AlarmDecisonProcessor;
import com.meituan.pay.finsecurity.service.event.EventProcessor;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hhhb
 * @date 2020/10/29 2:44 下午
 */

/**
 * eventData同步入口
 */
public class EventServiceImpl implements EventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private EventProcessor eventProcessor;

    @Autowired
    private DataService dataService;

    private String obtainDataJson(String eventData, String tradeData) {
        ContextData contextData = new ContextData();
        contextData.setEventData(eventData);
        contextData.setTradeData(tradeData);
        return JacksonUtils.toJson(contextData);
    }

    @Override
    public EventNoticeResp eventNotice(EventNoticeReq req) throws TException {

        String eventData = "{\"partnerid\":1,\"business_type\":2}";

        String tradeData = "{}";


        //分账主单与子单不一致
        String decisionExpr = "return false;";

        DecisionRule decisionRule = new DecisionRule();
        decisionRule.setAlias("alarmTest");
        decisionRule.setExpr(decisionExpr);

        String dataJson = obtainDataJson(eventData, tradeData);
        EventRule eventRule = new EventRule();

        List<Vector> vectorList = new ArrayList<>();
        Vector vector1 = new Vector();
        vector1.setAlias("partner");
        vector1.setExpr("return eventData.partnerid");
        vectorList.add(vector1);

        Vector vector2 = new Vector();
        vector2.setAlias("businessType");
        vector2.setExpr("return eventData.business_type");
        vectorList.add(vector2);

        eventRule.setVectorList(vectorList);

        AlarmDecisonProcessor decisonProcessor = new AlarmDecisonProcessor();
        decisonProcessor.decide(eventRule, decisionRule, dataJson);


        EventNoticeResp response = null;
//        try {
//            String eventCode = req.getEventCode();
//            TradeEvent tradeEvent = dataService.obtaintradeEvent(eventCode);
//            ContextData contextData = buildContext(req, tradeEvent);
//            ProcessResultEnum processResultEnum = eventProcessor.process(tradeEvent, contextData);
//            response = EventNoticeResp.genSuccessResponse(processResultEnum.getCode());
//            LOGGER.info("上游事件数据同步：请求报文：{}, 响应报文：{}", req, response);
//        } catch (Exception e) {
//            LOGGER.error("上游事件数据同步发生异常：请求报文：{}, 响应报文:{}, 异常:{}", req, response, LoggerUtils.getStackTrace(e));
//            response = EventNoticeResp.handleException(e);
//        }
        return response;
    }

    private ContextData buildContext(EventNoticeReq req, TradeEvent tradeEvent) {
        ContextData contextData = new ContextData();
        String eventData = req.getEventData();
        String tradeData = dataService.obtainTradeData(tradeEvent.getDataRuleList(), eventData);
        contextData.setTradeData(tradeData);
        contextData.setEventData(eventData);
        return contextData;
    }
}
