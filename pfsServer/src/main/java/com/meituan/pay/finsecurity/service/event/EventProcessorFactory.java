package com.meituan.pay.finsecurity.service.event;

import com.meituan.pay.finsecurity.dao.repository.DataRuleRepo;
import com.meituan.pay.finsecurity.dao.repository.DicisionRepo;
import com.meituan.pay.finsecurity.dao.repository.TradeEventRepo;
import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.pay.finsecurity.po.DicisionRule;
import com.meituan.pay.finsecurity.po.TradeEvent;
import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import com.meituan.pay.finsecurity.service.data.DataQueryProcessor;
import com.meituan.pay.finsecurity.service.data.RpcDataQueryProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */
@Service
public class EventProcessorFactory {
    private volatile Map<String, EventProcessor> eventProcessorMap;

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TradeEventRepo tradeEventRepo;
    @Autowired
    private DataRuleRepo dataRuleRepo;
    @Autowired
    private DicisionRepo dicisionRepo;

    @PostConstruct
    public void init() {
        eventProcessorMap = initEventProcessorMap();
    }

    public EventProcessor getProcessor(String eventCode) {
        if (eventProcessorMap.containsKey(eventCode)) {
            return eventProcessorMap.get(eventCode);
        }

        throw new RuntimeException(String.format("unsupport event code: %", eventCode));
    }

    private Map<String, EventProcessor> initEventProcessorMap() {
        Map<String, EventProcessor> eventProcessorMap = new HashMap<>();
        List<TradeEvent> tradeEventList = tradeEventRepo.selectByOnStatus();
        for (TradeEvent tradeEvent : tradeEventList) {
            List<DataRule> dataRuleList = dataRuleRepo.selectByEventId(tradeEvent.getId());
            List<DicisionRule> dicisionRuleList = dicisionRepo.selectByEventId(tradeEvent.getId());
            EventProcessor processor = applicationContext.getBean(EventProcessor.class);
            processor.setTradeEvent(tradeEvent);
            processor.setDataRuleList(dataRuleList);
            processor.setDicisionRuleList(dicisionRuleList);
            eventProcessorMap.put(tradeEvent.getCode(), processor);
        }
        return eventProcessorMap;
    }
}
