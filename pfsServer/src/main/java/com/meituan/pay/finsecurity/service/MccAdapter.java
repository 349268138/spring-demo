package com.meituan.pay.finsecurity.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Sets;
import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.po.TradeEvent;
import com.sankuai.meituan.config.MtConfigClient;
import org.apache.commons.collections.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author hhhb
 * @date 2020/11/3 7:29 下午
 */
@Component
public class MccAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MccAdapter.class);

    public static final String EVENTDATAMAP_JSON = "eventdata_map_json";

    @Autowired
    private MtConfigClient mtConfigClient;

    public String getString(String key, String defaultValue) {
        String eventDataMapJson = defaultValue;
        try{
            eventDataMapJson = mtConfigClient.getValue(key);
            return eventDataMapJson;
        } catch (Exception e) {
            logger.error("Mcc获取配置异常，key: {}, exception: {}", EVENTDATAMAP_JSON, LoggerUtils.getStackTrace(e));
        }
        return eventDataMapJson;
    }
}
