package com.meituan.pay.finsecurity.adapter;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.constant.MccConstant;
import com.meituan.pay.finsecurity.po.TradeEvent;
import com.sankuai.meituan.config.MtConfigClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * @author hhhb
 * @date 2020/11/3 7:29 下午
 */
@Service
public class MccAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MccAdapter.class);

    @Autowired
    private MtConfigClient mtConfigClient;

    private Map<String, TradeEvent> eventDataMap = Collections.emptyMap();

    @PostConstruct
    public void init() {
        initEventDataMap();
        initEventDataMapMccEvent();
    }

    private void initEventDataMap() {
        eventDataMap = convertUpdateEventDataMapJson(mtConfigClient.getValue(MccConstant.EVENTDATAMAP_KEY));
    }

    private void initEventDataMapMccEvent() {
        mtConfigClient.addListener(MccConstant.EVENTDATAMAP_KEY, ((key, oldValue, newValue) -> {
            try{
                eventDataMap = convertUpdateEventDataMapJson(newValue);
            } catch (Exception e) {
                logger.error("initEventDataMapMccEvent fail. key: {}, oldValue: {}, newValue: {}, exception: {}", key, oldValue, newValue, LoggerUtils.getStackTrace(e));
            }
        }));
    }

    private Map<String, TradeEvent> convertUpdateEventDataMapJson(String EventDataMapJson) {
        if(StringUtils.isEmpty(EventDataMapJson)) {
            return Collections.EMPTY_MAP;
        }
        Map<String, TradeEvent> eventDataMap = JacksonUtils.jsonToBeanMap(EventDataMapJson, TradeEvent.class);
        if(Objects.nonNull(eventDataMap)) {
            return eventDataMap;
        }
        throw new RuntimeException("parse eventDataMap string to object fail. eventDataMap: " + eventDataMap);
    }

    public Map<String, TradeEvent> getEventDataMap() {
        return eventDataMap;
    }
}
