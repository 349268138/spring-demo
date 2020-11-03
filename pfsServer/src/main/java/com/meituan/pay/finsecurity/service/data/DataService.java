package com.meituan.pay.finsecurity.service.data;

import com.meituan.pay.finsecurity.po.TradeEvent1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hhhb
 * @date 2020/11/3 2:28 下午
 */
@Service
public class DataService {
    private static final Logger logger = LoggerFactory.getLogger(DataService.class);
    private Map<String, TradeEvent1> eventDataMap = new HashMap<>();

    @PostConstruct
    public void initEventData(String eventCode) {
        eventDataMap.put(eventCode, obtainEventRule(eventCode));
    }

    public TradeEvent1 obtainEventRule(String eventCode) {

        return null;
    }

    public String obtainTradeData(String eventCode) {

        return null;
    }
}
