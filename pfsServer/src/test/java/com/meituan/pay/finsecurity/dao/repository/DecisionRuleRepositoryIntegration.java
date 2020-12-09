package com.meituan.pay.finsecurity.dao.repository;

import com.dianping.zebra.group.router.ZebraForceMasterHelper;
import com.meituan.pay.finsecurity.dao.mapper.DecisionRuleRepo;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.DecisionRuleExample;
import com.meituan.pay.finsecurity.po.enums.StatusEnum;
import com.meituan.pay.finsecurity.po.enums.TypeEnum;
import deps.redis.clients.util.CollectionUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author wangjinping
 * @date 11/26/2020 13:56 PM
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-db.xml")
public class DecisionRuleRepoIntegration {

    @Autowired
    private DecisionRuleRepo decisionRuleRepo;

    @Test
    public void test001SelectByEventId() {
//        DecisionRule decisionRule = obtainDecisionRule();
//        int affectLine = decisionRuleRepo.selectByEventId(decisionRule);
//        System.out.println(decisionRule);
//
//        Assert.assertTrue(affectLine > 0);
    }

    @Test
    public void test002SelectByPrimaryKeyTest() {
//        DecisionRule decisionRule = obtainDecisionRule();
//        decisionRuleRepo.insertSelective(decisionRule);
//
//        ZebraForceMasterHelper.forceMasterInLocalContext();
//        DecisionRule decisionRuleDB = decisionRuleRepo.selectByPrimaryKey(decisionRule.getId());
//        ZebraForceMasterHelper.clearLocalContext();
//        System.out.println(decisionRuleDB);
//
//        Assert.assertTrue(decisionRule.equals(decisionRuleDB));
    }

    @Test
    public void test003SelectByExampleTest() {
//        DecisionRuleExample example = new DecisionRuleExample();
//        example.createCriteria().andStatusEqualTo(StatusEnum.ON);
//
//        ZebraForceMasterHelper.forceMasterInLocalContext();
//        List<DecisionRule> decisionRuleListDB = decisionRuleRepo.selectByExample(example);
//        Assert.assertTrue(CollectionUtils.isNotEmpty(decisionRuleListDB));
    }









}