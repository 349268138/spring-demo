package com.meituan.pay.finsecurity.adapter;

import com.meituan.funds.simple.util.LoggerUtils;
import com.sankuai.meituan.config.MtConfigClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hhhb
 * @date 2020/11/3 7:29 下午
 */
@Service
public class MccAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MccAdapter.class);

    public static final String EVENTDATAMAP_JSON = "eventdata_map_json";

    @Autowired
    private MtConfigClient mtConfigClient;

    public String getString(String key, String defaultValue) {
        String eventDataMapJson = defaultValue;
        try{
            eventDataMapJson = mtConfigClient.getValue(key);
        } catch (Exception e) {
            logger.error("Mcc获取配置异常，key: {}, exception: {}", key, LoggerUtils.getStackTrace(e));
        }
        return eventDataMapJson;
    }
}
