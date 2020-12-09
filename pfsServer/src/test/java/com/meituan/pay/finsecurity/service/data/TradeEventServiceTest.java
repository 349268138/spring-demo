package com.meituan.pay.finsecurity.service.data;

import com.meituan.pay.finsecurity.dao.repository.DataRuleRepo;
import com.meituan.pay.finsecurity.dao.repository.DecisionRuleRepo;
import com.meituan.pay.finsecurity.dao.repository.EventRuleRepo;
import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.TradeEvent;
import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import com.meituan.pay.finsecurity.po.enums.StatusEnum;
import com.meituan.pay.finsecurity.po.enums.TypeEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author hhhb
 * @date 2020/12/9 8:25 下午
 */
public class TradeEventServiceTest {

    @InjectMocks
    private TradeEventService tradeEventService;

    @Mock
    private DataRuleRepo dataRuleRepo;

    @Mock
    private DecisionRuleRepo decisionRuleRepo;

    @Mock
    private EventRuleRepo eventRuleRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        List<DataRule> dataRuleList = new LinkedList<>();
        DataRule dataRule = new DataRule();
        dataRule.setAddress("com.sankuai.pay.fundstransfer.paycore:9006");
        dataRule.setAlias("paycore");
        dataRule.setEventId(1L);
        dataRule.setId(1L);
        dataRule.setKeyExpr("if(eventData.business_type == 1 && !eventData.trade_no.equals(\"\")) return eventData.trade_no else return \"\"");
        dataRule.setName("付款核心数据配置");
        dataRule.setType(DataAccessTypeEnum.RPC);
        dataRuleList.add(dataRule);
//        Mockito.doReturn(dataRuleList).when(dataRuleRepo.selectByEventId(1L));
        Mockito.when(dataRuleRepo.selectByEventId(1L)).thenReturn(dataRuleList);

        List<DecisionRule> decisionRuleList = new LinkedList<>();
        DecisionRule decisionRule = new DecisionRule();
        decisionRule.setAlias("monitor_complete");
        decisionRule.setEventId(1L);
        decisionRule.setExpr("if(eventData.status == 64 || eventData.status == 128 || eventData.status == 160) return 1 else return 0");
        decisionRule.setId(1L);
        decisionRule.setName("付款平台完成监控");
        decisionRule.setStatus(StatusEnum.ON);
        decisionRule.setType(TypeEnum.MONITOR);
        decisionRuleList.add(decisionRule);

//        Mockito.doReturn(dataRuleList).when(decisionRuleRepo.selectByEventId(1L));
        Mockito.when(decisionRuleRepo.selectByEventId(1L)).thenReturn(decisionRuleList);

        EventRule eventRule = new EventRule();
        eventRule.setCode("payplatform_complete_event");
        eventRule.setId(1L);
        eventRule.setName("付款平台完成事件");
        List<EventRule> eventRuleList = new LinkedList<>();
        eventRuleList.add(eventRule);

//        Mockito.doReturn(eventRuleList).when(eventRuleRepo.selectByOnStatus());
        Mockito.when(eventRuleRepo.selectByOnStatus()).thenReturn(eventRuleList);
    }

    @Test
    public void refreshTradeEventTest() {
        tradeEventService.refreshTradeEvent();
        Map<String, TradeEvent> tradeEventMap = tradeEventService.obtainTradeEventMapCache();
        Assert.assertNotNull(tradeEventMap.get("payplatform_complete_event"));
    }

}
