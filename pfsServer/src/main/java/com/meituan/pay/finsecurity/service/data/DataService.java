package com.meituan.pay.finsecurity.service.data;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.pay.common.framework.JacksonUtil;
import com.meituan.pay.finsecurity.po.TradeEvent;
import com.meituan.pay.finsecurity.service.MccAdapter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    public TradeEvent obtaintradeEvent(String eventCode) {
        return eventDataMap.get(eventCode);
    }

    public String obtainTradeData(String eventData, String eventCode) {
        TradeEvent tradeEvent = obtaintradeEvent(eventCode);
        return tradeDataService.queryTradeData(tradeEvent.getDataRuleList(), eventData);
    }
}
