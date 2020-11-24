package com.meituan.pay.finsecurity.adapter;

import com.meituan.pay.finsecurity.po.TradeEvent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.Map;

/**
 * @author hhhb
 * @date 2020/11/4 2:27 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-MccAdapterIntegration.xml")
public class MccAdapterIntegration {

    @Autowired
    private MccAdapter mccAdapter;

    private Map<String, TradeEvent> eventDataMap = Collections.emptyMap();

    @Test
    public void initEventDataMapTest() {
        mccAdapter.init();
        eventDataMap = mccAdapter.getEventDataMap();
        Assert.assertNotNull(eventDataMap);
    }
}
