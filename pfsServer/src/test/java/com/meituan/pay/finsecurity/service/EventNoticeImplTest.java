package com.meituan.pay.finsecurity.service;

import com.meituan.pay.finsecurity.po.ContextData;
import com.meituan.pay.finsecurity.po.TradeEvent;
import com.meituan.pay.finsecurity.po.enums.ProcessResultEnum;
import com.meituan.pay.finsecurity.sdk.dto.req.EventNoticeReq;
import com.meituan.pay.finsecurity.sdk.dto.resp.EventNoticeResp;
import com.meituan.pay.finsecurity.service.data.DataService;
import com.meituan.pay.finsecurity.service.event.EventProcessor;
import org.apache.thrift.TException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author hhhb
 * @date 2020/10/29 2:44 下午
 */
public class EventNoticeImplTest {
    @InjectMocks
    EventNoticeImpl eventNoticeImpl;

    @Mock
    private EventProcessor eventProcessor;

    @Mock
    private DataService dataService;

    private EventNoticeReq req = null;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        req = new EventNoticeReq();
        req.setEventCode("fundsRequest");
        req.setEventData("event_code");
        req.setEventTime(2333L);
        req.setRepeated(false);

        when(dataService.obtainTradeData(req.getEventData(), req.getEventCode())).thenReturn("trade_data");

        TradeEvent tradeEvent = new TradeEvent();
        when(dataService.obtaintradeEvent(req.getEventCode())).thenReturn(tradeEvent);
        when(eventProcessor.process(any(), any())).thenReturn(ProcessResultEnum.PASS);

    }

    @Test
    public void eventNoticeTest() throws TException {
        EventNoticeResp resp = eventNoticeImpl.eventNotice(req);
        Assert.assertEquals(new Integer(1), resp.getStatus().getValue());
    }


}
