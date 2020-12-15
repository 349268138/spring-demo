package com.meituan.pay.finsecurity.service.data;

import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.pay.finsecurity.po.TradeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author hhhb
 * @date 2020/11/3 2:28 下午
 */
@Service
public class DataService {
    private static final Logger logger = LoggerFactory.getLogger(DataService.class);
    @Autowired
    private TradeDataService tradeDataService;

    @Autowired
    private TradeEventService tradeEventService;

    public TradeEvent obtaintradeEvent(String eventCode){
        if(Objects.nonNull(tradeEventService.obtainTradeEventMapCache().get(eventCode))) {
            return tradeEventService.obtainTradeEventMapCache().get(eventCode);
        }

        throw new RuntimeException(String.format("eventcode not exist. eventCode: %s", eventCode));
    }

    public String obtainTradeData(List<DataRule> dataRuleList, String eventData) {
        String tradeData = tradeDataService.queryTradeData(dataRuleList, eventData);
        return tradeData;
    }
}
