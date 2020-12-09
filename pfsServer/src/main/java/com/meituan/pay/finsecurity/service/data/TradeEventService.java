package com.meituan.pay.finsecurity.service.data;

import com.meituan.pay.finsecurity.dao.repository.DataRuleRepo;
import com.meituan.pay.finsecurity.dao.repository.DecisionRuleRepo;
import com.meituan.pay.finsecurity.dao.repository.EventRuleRepo;
import com.meituan.pay.finsecurity.po.TradeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
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

    public Map<String, TradeEvent> obtainEventDataMap() {
        Map<String, TradeEvent> tradeEventMap = new HashMap<>();

    }

}
