package com.meituan.pay.finsecurity.controller;

import com.github.pagehelper.Page;
import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.dao.repository.DecisionRuleRepo;
import com.meituan.pay.finsecurity.dao.repository.EventRuleRepo;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.service.data.TradeEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author hhhb
 * @date 2020/12/10 4:15 下午
 */
@RestController
@RequestMapping(value = "api")
public class RuleController {
    Logger logger = LoggerFactory.getLogger(RuleController.class);

    @Autowired
    private DecisionRuleRepo decisionRuleRepo;

    @Autowired
    private EventRuleRepo eventRuleRepo;

    @Autowired
    private TradeEventService tradeEventService;

    @RequestMapping(value = "decision-search")
    public String decisionSearch(DecisionRule decisionRule,
                                 @RequestParam(value = "pageNum") int pageNum,
                                 @RequestParam(value = "pageSize") int pageSize){
        Map<String, Object> decisionRuleMap = new HashMap<>();
        try{
            Map<String, Object> searchData = obtainSearchData(decisionRule, pageNum, pageSize);
            decisionRuleMap = obtainSuccessResult(searchData);
            logger.info("decisionRule query succeeded, decisionRule: {}, pageNum: {}, pageSize: {}, decisionRuleMap: {}", decisionRule, pageNum, pageSize, decisionRuleMap);
        } catch (Exception e) {
            logger.error("decisionRule query failed, decisionRule: {}, pageNum: {}, pageSize: {} : {}, exception: {}", decisionRule, pageNum, pageSize, LoggerUtils.getStackTrace(e));
            decisionRuleMap = obtainErrorResult(e.getMessage());
        }
        return JacksonUtils.toJson(decisionRuleMap);

    }

    @RequestMapping(value = "decision-add")
    public String decisionAdd(DecisionRule decisionRule){
        Map<String, Object> decisionRuleMap = new HashMap<>();
        try{
            decisionRuleRepo.insertBySelective(decisionRule);
            tradeEventService.refreshTradeEvent();
            decisionRuleMap = obtainSuccessResult(decisionRule.getId());
            logger.info("decisionRule add succeeded, decisionRule: {}, decisionRuleMap: {}", decisionRule, decisionRuleMap);
        } catch (Exception e) {
            logger.error("decisionRule add failed, decisionRule: {}, exception: {}", decisionRule, LoggerUtils.getStackTrace(e));
            decisionRuleMap = obtainErrorResult(e.getMessage());
        }
        return JacksonUtils.toJson(decisionRuleMap);
    }

    @RequestMapping(value = "decision-update")
    public String decisionUpdate(DecisionRule decisionRule){
        Map<String, Object> decisionRuleMap = new HashMap<>();
        try{
            decisionRuleRepo.updateByIdSelective(decisionRule);
            tradeEventService.refreshTradeEvent();
            decisionRuleMap = obtainSuccessResult(decisionRule.getId());
            logger.info("decisionRule update succeeded, decisionRule: {}, decisionRuleMap: {}", decisionRule, decisionRuleMap);
        } catch (Exception e) {
            logger.error("decisionRule update failed, decisionRule: {}, exception : {}", decisionRule, LoggerUtils.getStackTrace(e));
            decisionRuleMap = obtainErrorResult(e.getMessage());
        }
        return JacksonUtils.toJson(decisionRuleMap);
    }

    @RequestMapping(value = "decision-delete")
    public String decisionDelete(DecisionRule decisionRule){
        Map<String, Object> decisionRuleMap = new HashMap<>();
        try{
            decisionRuleRepo.deleteByPrimaryKey(decisionRule.getId());
            tradeEventService.refreshTradeEvent();
            decisionRuleMap = obtainSuccessResult(decisionRule.getId());
            logger.info("decisionRule delete succeeded, decisionRule: {}, decisionRuleMap: {}", decisionRule, decisionRuleMap);
        } catch (Exception e) {
            logger.error("decisionRule delete failed, decisionRule: {}, exception : {}", decisionRule, LoggerUtils.getStackTrace(e));
            decisionRuleMap = obtainErrorResult(e.getMessage());
        }
        return JacksonUtils.toJson(decisionRuleMap);
    }

    @RequestMapping(value = "event-search-all")
    public String eventSearchAll() {
        Map<String, Object> eventRuleMap = new HashMap<>();
        try{
            eventRuleMap = obtainSuccessResult(eventRuleRepo.selectAll());
            logger.info("event search all succeeded, eventRuleMap: {}", eventRuleMap);
        } catch (Exception e) {
            logger.error("event search all failed, exception : {}", LoggerUtils.getStackTrace(e));
            eventRuleMap = obtainErrorResult(e.getMessage());
        }
        return JacksonUtils.toJson(eventRuleMap);
    }

    private Map<String, Object> obtainSuccessResult(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("data", data);
        return result;
    }

    private Map<String, Object> obtainErrorResult(String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 1);
        result.put("msg", msg);
        return result;
    }

    private Map<String, Object> obtainSearchData(DecisionRule decisionRule, int pageNum, int pageSize) {
        Page page = decisionRuleRepo.selectExampleByPage(decisionRule, pageNum, pageSize);
        Map<String, Object> searchData = new HashMap<>();
        searchData.put("total", page.getTotal());
        searchData.put("list", page);
        return searchData;
    }
}
