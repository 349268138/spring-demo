package com.meituan.pay.finsecurity.dao.repository;

import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.enums.StatusEnum;
import com.meituan.pay.finsecurity.po.enums.TypeEnum;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author wangjinping
 * @date 11/26/2020 13:56 PM
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-db.xml")
public class EventRuleRepoIntegration {

    @Autowired
    private EventRuleRepo eventRuleRepo;

    @Test
    public void test001SelectByOnStatus() {
        List<EventRule> eventRuleList = eventRuleRepo.selectByOnStatus();
        System.out.println(eventRuleList);
        Assert.assertTrue(!CollectionUtils.isEmpty(eventRuleList));
    }

    @Test
    public void test002SelectAll() {
        List<EventRule> eventRuleList = eventRuleRepo.selectAll();
        System.out.println(eventRuleList);
        Assert.assertTrue(!CollectionUtils.isEmpty(eventRuleList));
    }
}