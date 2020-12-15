package com.meituan.pay.finsecurity.controller;

import com.meituan.pay.finsecurity.po.DecisionRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author hhhb
 * @date 2020/12/14 9:32 下午
 */
public class RuleControllerTest {

    Logger logger = LoggerFactory.getLogger(RuleControllerTest.class);


    @Test
    public void test() {
        DecisionRule decisionRule = new DecisionRule();
        decisionRule.setId(1L);
        decisionRule.setName("test-hb");
        List<DecisionRule> decisionRuleList = new LinkedList<>();
        decisionRuleList.add(decisionRule);
        Map<String, Object> decisionRuleMap = new HashMap<>();
        decisionRuleMap.put("code", 0);
        decisionRuleMap.put("msg", "");
        decisionRuleMap.put("data", decisionRuleList);
//        System.out.println("1111111111" + decisionRuleMap);
//        System.out.println("2222222222" + JacksonUtils.toJson(decisionRuleMap));
        logger.debug("decisionRule query succeeded: {}", decisionRuleMap);
    }
}
