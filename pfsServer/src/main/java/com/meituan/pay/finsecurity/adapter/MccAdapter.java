package com.meituan.pay.finsecurity.adapter;

import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.constant.MccConstant;
import com.sankuai.meituan.config.MtConfigClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author hhhb
 * @date 2020/11/3 7:29 下午
 */
@Service
public class MccAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MccAdapter.class);

    @Autowired
    private MtConfigClient mtConfigClient;

    private Set<String> longConvertSet = Collections.EMPTY_SET;

    @PostConstruct
    public void init() {
        initLongConvertSet();
        initLongConvertSetMccEvent();
    }

    private void initLongConvertSet() {
        longConvertSet = convertUpdateLongConvertSet(mtConfigClient.getValue(MccConstant.LONG_CONVERT_SET_KEY));
    }

    private void initLongConvertSetMccEvent() {
        mtConfigClient.addListener(MccConstant.LONG_CONVERT_SET_KEY, ((key, oldValue, newValue) -> {
            try {
                longConvertSet = convertUpdateLongConvertSet(newValue);
            } catch (Exception e) {
                logger.error("initLongConvertSetMccEvent fail. key: {}, oldValue: {}, newValue: {}, exception: {}", key, oldValue, newValue, LoggerUtils.getStackTrace(e));
            }
        }));
    }

    private Set<String> convertUpdateLongConvertSet(String data) {
        if (StringUtils.isEmpty(data)) {
            return Collections.EMPTY_SET;
        }
        Set<String> longConvertSet = new HashSet<>();
        String[] values = data.split(",");
        for (String value : values) {
            longConvertSet.add(value);
        }
        return longConvertSet;
    }

    public Set<String> getLongConvertSet() {
        return longConvertSet;
    }
}
