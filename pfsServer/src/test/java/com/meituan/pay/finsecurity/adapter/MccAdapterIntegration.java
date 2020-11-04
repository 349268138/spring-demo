package com.meituan.pay.finsecurity.adapter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author hhhb
 * @date 2020/11/4 2:27 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-MccAdapterIntegration.xml")
public class MccAdapterIntegration {

    @Autowired
    private MccAdapter mccAdapter;

    @Test
    public void getStringTest(){
        String value = mccAdapter.getString(MccAdapter.EVENTDATAMAP_JSON, "hb");
        Assert.assertEquals("test", value);
    }
}
