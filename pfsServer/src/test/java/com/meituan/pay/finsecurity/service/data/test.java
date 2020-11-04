package com.meituan.pay.finsecurity.service.data;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.TradeEvent;
import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import com.meituan.pay.finsecurity.po.enums.TypeEnum;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hhhb
 * @date 2020/11/4 4:25 下午
 */
public class test {
    private Map<String, TradeEvent> eventDataMap = new HashMap<>();
    private TradeEvent tradeEvent = null;

    @Before
    public void setUp(){
        List<DataRule> dataRuleList = new ArrayList<>();;
        List<DecisionRule> decisionRuleList = new ArrayList<>();
        EventRule eventRule = new EventRule();
        tradeEvent = new TradeEvent();

        eventRule.setId(1L);
        eventRule.setCode("fundsRequest");
        eventRule.setName("eventRule_01");
        eventRule.setVector("{\"alias\":\"er_01\", \"name\":事件规则01\"\", \"expr\":\"true\"}");

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
    }

    @Test
    public void test(){
        String json = JacksonUtils.toJson(eventDataMap);
        System.out.println(json);
    }
}
