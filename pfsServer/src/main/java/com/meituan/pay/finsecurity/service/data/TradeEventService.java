package com.meituan.pay.finsecurity.service.data;

import com.dianping.squirrel.common.util.CollectionUtils;
import com.meituan.pay.finsecurity.dao.repository.DataRuleRepo;
import com.meituan.pay.finsecurity.dao.repository.DecisionRuleRepo;
import com.meituan.pay.finsecurity.dao.repository.EventRuleRepo;
import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.TradeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hhhb
 * @date 2020/12/9 5:16 下午
 */
@Component
public class TradeEventService {
    private static final Logger logger = LoggerFactory.getLogger(TradeEventService.class);

    @Autowired
    private DataRuleRepo dataRuleRepo;

    @Autowired
    private DecisionRuleRepo decisionRuleRepo;

    @Autowired
    private EventRuleRepo eventRuleRepo;

    private Map<String, TradeEvent> tradeEventMap;

    @PostConstruct
    public void init() {
        tradeEventMap = obtainEventDataMap();
    }

    public void refreshTradeEvent() {
        tradeEventMap = obtainEventDataMap();
    }

    private Map<String, TradeEvent> obtainEventDataMap() {
        Map<String, TradeEvent> tempTradeEventMap = new HashMap<>();
        List<EventRule> eventRuleList = eventRuleRepo.selectByOnStatus();
        if(CollectionUtils.isNotEmpty(eventRuleList)) {
            for(EventRule eventRule : eventRuleList) {
                List<DataRule> dataRuleList = dataRuleRepo.selectByEventId(eventRule.getId());
                List<DecisionRule> decisionRuleList = decisionRuleRepo.selectByEventId(eventRule.getId());
                tempTradeEventMap.put(eventRule.getCode(), buildTradeEvent(dataRuleList, decisionRuleList, eventRule));
            }
        }
        return tempTradeEventMap;
    }

    private TradeEvent buildTradeEvent(List<DataRule> dataRuleList, List<DecisionRule> decisionRuleList, EventRule eventRule) {
        TradeEvent tradeEvent = new TradeEvent();
        tradeEvent.setEventRule(eventRule);
        tradeEvent.setDecisionRuleList(decisionRuleList);
        tradeEvent.setDataRuleList(dataRuleList);
        return tradeEvent;
    }

    public Map<String, TradeEvent> obtainTradeEventMapCache() {
        return tradeEventMap;
    }

}
