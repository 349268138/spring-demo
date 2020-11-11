package com.meituan.pay.finsecurity.adapter;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.pay.finsecurity.constant.MccConstant;
import com.meituan.pay.finsecurity.po.*;
import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import com.meituan.pay.finsecurity.po.enums.StatusEnum;
import com.meituan.pay.finsecurity.po.enums.TypeEnum;
import com.sankuai.meituan.config.MtConfigClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.doReturn;

/**
 * @author hhhb
 * @date 2020/11/4 2:27 下午
 */
public class MccAdapterTest {
    @InjectMocks
    private MccAdapter mccAdapter;

    @Mock
    private MtConfigClient mtConfigClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void initEventDataMapTest() {
        Map<String, TradeEvent> eventDataMap = new HashMap<>();
        TradeEvent tradeEvent = null;

        doReturn(null).when(mtConfigClient).getValue(MccConstant.EVENTDATAMAP_KEY);
        mccAdapter.init();
        Assert.assertTrue(mccAdapter.getEventDataMap().isEmpty());

        doReturn("").when(mtConfigClient).getValue(MccConstant.EVENTDATAMAP_KEY);
        mccAdapter.init();
        Assert.assertTrue(mccAdapter.getEventDataMap().isEmpty());

        doReturn("[").when(mtConfigClient).getValue(MccConstant.EVENTDATAMAP_KEY);
        try {
            mccAdapter.init();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof RuntimeException);
        }

        List<DataRule> dataRuleList = new ArrayList<>();
        ;
        List<DecisionRule> decisionRuleList = new ArrayList<>();
        List<Vector> vectorList = new ArrayList<>();
        EventRule eventRule = new EventRule();
        tradeEvent = new TradeEvent();
        Vector vector = new Vector();
        vector.setAlias("er_01");
        vector.setName("事件规则01");
        vector.setExpr("true");
        vectorList.add(vector);
        eventRule.setId(1L);
        eventRule.setCode("fundsRequest");
        eventRule.setName("eventRule_01");
        eventRule.setVectorList(vectorList);
        DataRule dataRule = new DataRule();
        dataRule.setId(1L);
        dataRule.setEventId(1L);
        dataRule.setName("付款核心数据配置");
        dataRule.setAlias("paycore");
        dataRule.setAddress("com.sankuai.pay.fundstransfer.paycore:9006:localhost");
        dataRule.setType(DataAccessTypeEnum.RPC);
        dataRule.setKeyExpr("eventData.trade_no");
        dataRuleList.add(dataRule);
        DecisionRule decisionRule = new DecisionRule();
        decisionRule.setId(1L);
        decisionRule.setEventId(1L);
        decisionRule.setName("决策规则_01");
        decisionRule.setAlias("decisionRule_01");
        decisionRule.setType(TypeEnum.ALARM);
        decisionRule.setExpr("true");
        decisionRuleList.add(decisionRule);
        tradeEvent.setEventRule(eventRule);
        tradeEvent.setDataRuleList(dataRuleList);
        tradeEvent.setDecisionRuleList(decisionRuleList);
        eventDataMap.put(eventRule.getCode(), tradeEvent);

        doReturn(JacksonUtils.toJson(eventDataMap)).when(mtConfigClient).getValue(MccConstant.EVENTDATAMAP_KEY);
        mccAdapter.init();
        Assert.assertFalse(mccAdapter.getEventDataMap().isEmpty());
    }

    @Test
    public void createTest() {
        Map<String, TradeEvent> eventDataMap = new HashMap<>();

        TradeEvent tradeEvent = new TradeEvent();

        EventRule eventRule = new EventRule();
        eventRule.setId(1L);
        eventRule.setCode("payplatform_complete_event");
        eventRule.setName("付款平台完成事件");
        List<Vector> vectorList = new ArrayList<>();
        Vector vectorPartnerId = new Vector();
        vectorPartnerId.setAlias("partner");
        vectorPartnerId.setName("业务线");
        vectorPartnerId.setExpr("return eventData.partnerid");
        vectorList.add(vectorPartnerId);
        Vector vectorBusinessType = new Vector();
        vectorBusinessType.setAlias("businessType");
        vectorBusinessType.setName("业务类型");
        vectorBusinessType.setExpr("return eventData.business_type");
        vectorList.add(vectorBusinessType);
        eventRule.setVectorList(vectorList);
        tradeEvent.setEventRule(eventRule);

        List<DataRule> dataRuleList = new ArrayList<>();
        DataRule dataRule = new DataRule();
        dataRule.setId(1L);
        dataRule.setEventId(1L);
        dataRule.setName("付款核心数据配置");
        dataRule.setAlias("paycore");
        dataRule.setAddress("com.sankuai.pay.fundstransfer.paycore:9006:localhost");
        dataRule.setType(DataAccessTypeEnum.RPC);
        dataRule.setKeyExpr("eventData.trade_no");
        dataRuleList.add(dataRule);
        tradeEvent.setDataRuleList(dataRuleList);

        List<DecisionRule> decisionRuleList = new ArrayList<>();
        DecisionRule decisionRule = new DecisionRule();
        decisionRule.setId(1L);
        decisionRule.setEventId(1L);
        decisionRule.setAlias("monitor_complete");
        decisionRule.setName("付款平台完成监控");
        decisionRule.setExpr("if(eventData.status == 64 || eventData.status == 128 || eventData.status == 160) return 1 else return 0");
        decisionRule.setStatus(StatusEnum.ON);
        decisionRule.setType(TypeEnum.MONITOR);
        decisionRuleList.add(decisionRule);

        DecisionRule decisionRuleAlarm = new DecisionRule();
        decisionRuleAlarm.setId(1L);
        decisionRuleAlarm.setEventId(1L);
        decisionRuleAlarm.setAlias("monitor_complete");
        decisionRuleAlarm.setName("付款平台完成监控");
        decisionRuleAlarm.setExpr("if((eventData.status == 64 && tradeData.paycore.inStatus == \"SUCCESS\") || (eventData.status == 128 && tradeData.paycore.inStatus == \"FAIL\")) return true else return false");
        decisionRuleAlarm.setStatus(StatusEnum.ON);
        decisionRuleAlarm.setType(TypeEnum.ALARM);
        decisionRuleList.add(decisionRuleAlarm);

        tradeEvent.setDecisionRuleList(decisionRuleList);

        eventDataMap.put(eventRule.getCode(), tradeEvent);
        System.out.println(JacksonUtils.toJson(eventDataMap));
    }

}
