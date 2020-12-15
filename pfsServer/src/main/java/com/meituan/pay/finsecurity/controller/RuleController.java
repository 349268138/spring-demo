package com.meituan.pay.finsecurity.controller;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.dao.repository.DecisionRuleRepo;
import com.meituan.pay.finsecurity.po.DecisionRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hhhb
 * @date 2020/12/10 4:15 下午
 */
@RestController
@RequestMapping("/finSecurity")
public class RuleController {
    Logger logger = LoggerFactory.getLogger(RuleController.class);

    @Autowired
    private DecisionRuleRepo decisionRuleRepo;

    @RequestMapping(value = "api/decision-search")
    public String decisionSearch(DecisionRule decisionRule,@RequestParam(value = "pageNum") int pageNum,@RequestParam(value = "pageSize") int pageSize){
        Map<String, Object> decisionRuleMap = new HashMap<>();
        try{
//            List<DecisionRule> decisionRuleList = decisionRuleRepo.getAllByPage(decisionRule.getId(), decisionRule.getEventId(), decisionRule.getAlias(), decisionRule.getName(), pageNum, pageSize);
            List<DecisionRule> decisionRuleList = new ArrayList<>(); // todo
            decisionRuleMap.put("code", 0);
            decisionRuleMap.put("msg", "");
            decisionRuleMap.put("data", decisionRuleList);
            logger.info("decisionRule query succeeded, decisionRuleMap : {}", decisionRuleMap);
        } catch (Exception e) {
            decisionRuleMap.put("code", 1);
            decisionRuleMap.put("msg", e.getMessage());
            decisionRuleMap.put("data", null);
            logger.error("decisionRule query failed, exception : {}, decisionRuleMap : {}", LoggerUtils.getStackTrace(e), decisionRuleMap);
        }
        return JacksonUtils.toJson(decisionRuleMap);

    }

    @RequestMapping(value = "api/decision-add")
    public String decisionAdd(DecisionRule decisionRule,
                              @RequestParam(value = "name", required = false) String name){
        Map<String, Object> decisionRuleMap = new HashMap<>();
        try{
            decisionRuleRepo.insertBySelective(decisionRule);
            decisionRuleMap.put("code", 0);
            decisionRuleMap.put("msg", "");
            decisionRuleMap.put("data", decisionRule.getId());
            logger.info("decisionRule add succeeded: {}", decisionRuleMap);
        } catch (Exception e) {
            decisionRuleMap.put("code", 1);
            decisionRuleMap.put("msg", e.getMessage());
            decisionRuleMap.put("data", null);
            logger.error("decisionRule add failed, exception : {}, decisionRuleMap : {}", LoggerUtils.getStackTrace(e), decisionRuleMap);
        }
        return JacksonUtils.toJson(decisionRuleMap);
    }

    @RequestMapping(value = "api/decision-update")
    public String decisionUpdate(DecisionRule decisionRule){
        Map<String, Object> decisionRuleMap = new HashMap<>();

        try{
            decisionRuleRepo.updateByIdSelective(decisionRule);
            decisionRuleMap.put("code", 0);
            decisionRuleMap.put("msg", "");
            decisionRuleMap.put("data", decisionRule.getId());
            logger.info("decisionRule update succeeded: {}", decisionRuleMap);
        } catch (Exception e) {
            decisionRuleMap.put("code", 1);
            decisionRuleMap.put("msg", e.getMessage());
            decisionRuleMap.put("data", null);
            logger.error("decisionRule update failed, exception : {}, decisionRuleMap : {}", LoggerUtils.getStackTrace(e), decisionRuleMap);
        }
        return JacksonUtils.toJson(decisionRuleMap);
    }

    @RequestMapping(value = "api/decision-delete")
    public String decisionDelete(DecisionRule decisionRule){


        return "";
    }

    @RequestMapping(value = "api/event-search-all-code")
    public String eventSearchAllCode() {

        return "";
    }

}
