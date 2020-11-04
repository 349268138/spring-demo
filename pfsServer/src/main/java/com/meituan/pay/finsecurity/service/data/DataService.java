package com.meituan.pay.finsecurity.service.data;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.pay.finsecurity.po.TradeEvent;
import com.meituan.pay.finsecurity.adapter.MccAdapter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Map<String, TradeEvent> eventDataMap = new HashMap<>();

    @Autowired
    private TradeDataService tradeDataService;

    @Autowired
    private MccAdapter mccAdapter;

    @PostConstruct
    public void initEventData() {
        String eventDataMapJson =  mccAdapter.getString(MccAdapter.EVENTDATAMAP_JSON, "");
        if(!StringUtils.isEmpty(eventDataMapJson)) {
            eventDataMap = JacksonUtils.jsonToBeanMap(eventDataMapJson, TradeEvent.class);
        }
    }

    public TradeEvent obtaintradeEvent(String eventCode) throws NullPointerException{
        return eventDataMap.get(eventCode);
    }

    public String obtainTradeData(String eventData, String eventCode) throws Exception {
        String tradeData = null;
        TradeEvent tradeEvent = null;
        try{
            tradeEvent = obtaintradeEvent(eventCode);
            tradeData = tradeDataService.queryTradeData(tradeEvent.getDataRuleList(), eventData);
        } catch (NullPointerException npe) {
            logger.error("当前不支持{}类型的事件", eventCode);
            throw new RuntimeException();
        }
        return tradeData;
    }
}
