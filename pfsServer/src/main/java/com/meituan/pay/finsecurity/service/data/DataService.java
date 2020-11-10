package com.meituan.pay.finsecurity.service.data;

import com.meituan.pay.finsecurity.adapter.MccAdapter;
import com.meituan.pay.finsecurity.po.TradeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private MccAdapter mccAdapter;


    public TradeEvent obtaintradeEvent(String eventCode){
        if(Objects.nonNull(mccAdapter.getEventDataMap())) {
            return mccAdapter.getEventDataMap().get(eventCode);
        }

        throw new RuntimeException(String.format("eventcode not exist. eventCode: %s", eventCode));
    }

    public String obtainTradeData(String eventData, String eventCode) {
        TradeEvent tradeEvent = obtaintradeEvent(eventCode);
        String tradeData = tradeDataService.queryTradeData(tradeEvent.getDataRuleList(), eventData);
        return tradeData;
    }
}
