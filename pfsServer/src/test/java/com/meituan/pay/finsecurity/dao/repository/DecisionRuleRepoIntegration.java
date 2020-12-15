package com.meituan.pay.finsecurity.dao.repository;

import com.meituan.pay.finsecurity.po.DecisionRule;
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
public class DecisionRuleRepoIntegration {

    @Autowired
    private DecisionRuleRepo decisionRuleRepo;

    @Test
    public void test001SelectExampleByPage() {
        DecisionRule decisionRule = new DecisionRule();
        List<DecisionRule> decisionRuleList = decisionRuleRepo.selectExampleByPage(decisionRule, 1, 10);
        System.out.println(decisionRuleList);
        Assert.assertTrue(!CollectionUtils.isEmpty(decisionRuleList));

        decisionRule = new DecisionRule();
        decisionRule.setId(decisionRuleList.get(0).getId());
        decisionRuleList = decisionRuleRepo.selectExampleByPage(decisionRule, 1, 10);
        System.out.println(decisionRuleList);
        Assert.assertTrue(!CollectionUtils.isEmpty(decisionRuleList));

        decisionRule = new DecisionRule();
        decisionRule.setEventId(decisionRuleList.get(0).getEventId());
        decisionRuleList = decisionRuleRepo.selectExampleByPage(decisionRule, 1, 10);
        System.out.println(decisionRuleList);
        Assert.assertTrue(!CollectionUtils.isEmpty(decisionRuleList));

        decisionRule = new DecisionRule();
        decisionRule.setAlias(decisionRuleList.get(0).getAlias());
        decisionRuleList = decisionRuleRepo.selectExampleByPage(decisionRule, 1, 10);
        System.out.println(decisionRuleList);
        Assert.assertTrue(!CollectionUtils.isEmpty(decisionRuleList));

        decisionRule = new DecisionRule();
        decisionRule.setName(decisionRuleList.get(0).getName());
        decisionRuleList = decisionRuleRepo.selectExampleByPage(decisionRule, 1, 10);
        System.out.println(decisionRuleList);
        Assert.assertTrue(!CollectionUtils.isEmpty(decisionRuleList));
    }

    @Test
    public void test002InsertBySelective() {
        DecisionRule decisionRule = obtainDecisionRule();
        int affectLine = decisionRuleRepo.insertBySelective(decisionRule);
        Assert.assertTrue(affectLine != 0);
    }

    @Test
    public void test003UpdateByIdSelective() {
        DecisionRule decisionRule = obtainDecisionRule();
        decisionRuleRepo.insertBySelective(decisionRule);

        decisionRule.setEventId(System.currentTimeMillis());
        int affectLine = decisionRuleRepo.updateByIdSelective(decisionRule);
        Assert.assertTrue(affectLine != 0);
    }

    private DecisionRule obtainDecisionRule() {
        DecisionRule decisionRule = new DecisionRule();
        decisionRule.setId(System.currentTimeMillis());
        decisionRule.setEventId(System.currentTimeMillis());
        decisionRule.setAlias(String.valueOf(System.currentTimeMillis()));
        decisionRule.setName(String.valueOf(System.currentTimeMillis()));
        decisionRule.setExpr(String.valueOf(System.currentTimeMillis()));
        decisionRule.setStatus(StatusEnum.ON);
        decisionRule.setType(TypeEnum.MONITOR);
        return decisionRule;
    }

}