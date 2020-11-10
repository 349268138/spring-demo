package com.meituan.pay.finsecurity.service.event;

import com.meituan.pay.finsecurity.po.ContextData;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.TradeEvent;
import com.meituan.pay.finsecurity.po.enums.ProcessResultEnum;
import com.meituan.pay.finsecurity.po.enums.TypeEnum;
import com.meituan.pay.finsecurity.service.decision.DecisionProcessorFactory;
import com.meituan.pay.finsecurity.service.decision.MonitorDecisonProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author hhhb
 * @date 2020/11/4 2:35 下午
 */
public class EventProcessorTest {

    @InjectMocks
    private EventProcessor eventProcessor;

    @Mock
    private DecisionProcessorFactory decisionProcessorFactory;

    @Mock
    private MonitorDecisonProcessor monitorDecisonProcessor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(decisionProcessorFactory.obtainProcessor(TypeEnum.MONITOR)).thenReturn(monitorDecisonProcessor);
        when(monitorDecisonProcessor.decide(any(EventRule.class), any(DecisionRule.class), anyString())).thenReturn(ProcessResultEnum.PASS);
    }

    @Test
    public void processTest() {
        TradeEvent tradeEvent = obtainTradeEvent();
        ProcessResultEnum resultEnum = eventProcessor.process(tradeEvent, new ContextData());
        Assert.assertEquals(ProcessResultEnum.PASS, resultEnum);

        when(monitorDecisonProcessor.decide(any(EventRule.class), any(DecisionRule.class), anyString())).thenReturn(ProcessResultEnum.INTERCEPT);
        resultEnum = eventProcessor.process(tradeEvent, new ContextData());
        Assert.assertEquals(ProcessResultEnum.INTERCEPT, resultEnum);

        tradeEvent.setDecisionRuleList(null);
        resultEnum = eventProcessor.process(tradeEvent, new ContextData());
        Assert.assertEquals(ProcessResultEnum.PASS, resultEnum);
    }

    private TradeEvent obtainTradeEvent() {
        TradeEvent tradeEvent = new TradeEvent();

        tradeEvent.setEventRule(new EventRule());

        List<DecisionRule> decisionRuleList = new ArrayList<>();
        DecisionRule decisionRule = new DecisionRule();
        decisionRule.setType(TypeEnum.MONITOR);
        decisionRuleList.add(decisionRule);
        tradeEvent.setDecisionRuleList(decisionRuleList);

        return tradeEvent;
    }
}
