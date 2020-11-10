package com.meituan.pay.finsecurity.service.data;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.pay.finsecurity.adapter.MccAdapter;
import com.meituan.pay.finsecurity.constant.MccConstant;
import com.meituan.pay.finsecurity.po.TradeEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Map;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * @author hhhb
 * @date 2020/11/4 2:27 下午
 */
public class DataServiceTest {
    @Spy
    @InjectMocks
    private DataService dataService;

    @Mock
    private MccAdapter mccAdapter;

    @Mock
    private TradeDataService tradeDataService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
//        when(mccAdapter.getString(anyString(),anyString())).thenReturn(MccConstant.EVENTDATAMAP_VALUE);
    }

    @Test
    public void obtainTradeEventTest(){
        String eventCode = "fundsRequest";
        TradeEvent tradeEvent = dataService.obtaintradeEvent(eventCode);
        Assert.assertNotNull(tradeEvent);
        Assert.assertEquals(tradeEvent.getEventRule().getCode(), eventCode);
    }

    @Test
    public void obtainTradeDataTest(){
        TradeEvent tradeEvent = dataService.obtaintradeEvent("fundsRequest");
        when(dataService.obtaintradeEvent("fundsRequest")).thenReturn(tradeEvent);
        when(dataService.obtainTradeData("fundsRequest", "eventData")).thenReturn("trade_data");
        String tradeData = dataService.obtainTradeData("fundsRequest", "eventData");
        Assert.assertNotNull(tradeData);

    }

}
