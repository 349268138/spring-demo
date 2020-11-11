package com.meituan.pay.finsecurity.service.data;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.pay.finsecurity.adapter.MccAdapter;
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
        Map<String, TradeEvent> eventDataMap = JacksonUtils.jsonToBeanMap("{\"fundsRequest\":{\"dataRuleList\":[{\"address\":\"com.sankuai.pay.fundstransfer.paycore:9006:localhost\",\"alias\":\"paycore\",\"eventId\":1,\"id\":1,\"keyExpr\":\"eventData.trade_no\",\"name\":\"付款核心数据配置\",\"type\":\"RPC\"}],\"decisionRuleList\":[{\"alias\":\"decisionRule_01\",\"eventId\":1,\"expr\":\"true\",\"id\":1,\"name\":\"决策规则_01\",\"type\":\"ALARM\"}],\"eventRule\":{\"code\":\"fundsRequest\",\"id\":1,\"name\":\"eventRule_01\",\"vectorList\":[{\"alias\":\"er_01\",\"expr\":\"true\",\"name\":\"事件规则01\"}]}}}"
                , TradeEvent.class);
        when(mccAdapter.getEventDataMap()).thenReturn(eventDataMap);
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
        when(tradeDataService.queryTradeData(anyList(), eq("eventData"))).thenReturn("tradeData");
        String tradeData = dataService.obtainTradeData("fundsRequest","eventData");
        Assert.assertNotNull(tradeData);
    }

}
