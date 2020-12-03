package com.meituan.pay.finsecurity.adapter;

import com.meituan.pay.finsecurity.constant.SquirrelConstant;
import com.meituan.pay.finsecurity.po.TradeEvent;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author wangjinping
 * @date 11/26/2020 13:56 PM
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-SquirrelAdapterIntegration.xml")
public class SquirrelAdapterIntegration {

    @Autowired
    private SquirrelAdapter squirrelAdapter;

    @Test
    public void test001SetTest() {
        boolean result = squirrelAdapter.set(SquirrelConstant.FIN_SECURITY_CATEGORY, "2631008697", 9000);
        Assert.assertTrue(result);
    }

    @Test
    public void test002MGetTest() {
        List<String> keyList = new ArrayList<>();
        keyList.add("2631008697");
        String offlineData = squirrelAdapter.mGet(SquirrelConstant.FIN_SECURITY_CATEGORY, keyList);
        Assert.assertNotNull(offlineData);
        System.out.println(offlineData);
    }

    @Test
    public void test003HSetTest() {
        long result = squirrelAdapter.hSet(SquirrelConstant.FIN_SECURITY_CATEGORY, "2631008701", "averageMoney", new Long(67826965));
        Assert.assertTrue(result >= 0);


        result = squirrelAdapter.hSet(SquirrelConstant.FIN_SECURITY_CATEGORY, "2631008701", "averageCount", 1309876);
        Assert.assertTrue(result >= 0);

        result = squirrelAdapter.hSet(SquirrelConstant.FIN_SECURITY_CATEGORY, "2631008701", "maxMoney", 9200);
        Assert.assertTrue(result >= 0);
    }

    @Test
    public void test004HGetAllTest() {
        String key = "2631008701";
        String offlineData = squirrelAdapter.hGetAll(SquirrelConstant.FIN_SECURITY_CATEGORY, key);
        Assert.assertNotNull(offlineData);
        System.out.println(offlineData);
    }

    @Test
    public void test005HincrByTest() {
        String key = "2631008701";
        Long oldValue = Long.parseLong(squirrelAdapter.hGet(SquirrelConstant.FIN_SECURITY_CATEGORY, key, "averageMoney").toString());
        Long newValue = squirrelAdapter.hincrBy(SquirrelConstant.FIN_SECURITY_CATEGORY, key, "averageMoney", 1);
        Assert.assertTrue(newValue - oldValue >= 1);
    }
}