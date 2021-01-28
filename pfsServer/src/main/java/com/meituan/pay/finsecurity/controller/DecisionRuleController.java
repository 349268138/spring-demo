package com.meituan.pay.finsecurity.controller;

import com.github.pagehelper.Page;
import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.dao.repository.DecisionRuleRepo;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.service.data.TradeEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hhhb
 * @date 2020/12/10 4:15 下午
 */
@RestController
@RequestMapping(value = "api/decision")
public class DecisionRuleController {
    Logger logger = LoggerFactory.getLogger(DecisionRuleController.class);

    @Autowired
    private DecisionRuleRepo decisionRuleRepo;

    @Autowired
    private TradeEventService tradeEventService;

    @RequestMapping(method = RequestMethod.GET)
    public String decisionSearch(DecisionRule decisionRule, int pageNum,
                                 int pageSize){
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

    @RequestMapping(method = RequestMethod.POST)
    public String decisionAdd(@RequestBody DecisionRule decisionRule){
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

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public String decisionUpdate(@PathVariable long id, @RequestBody DecisionRule decisionRule){
        decisionRule.setId(id);
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

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public String decisionDelete(@PathVariable Long id){
        Map<String, Object> decisionRuleMap = new HashMap<>();
        try{
            decisionRuleRepo.deleteByPrimaryKey(id);
            tradeEventService.refreshTradeEvent();
            decisionRuleMap = obtainSuccessResult(id);
            logger.info("decisionRule delete succeeded, id: {}, decisionRuleMap: {}", id, decisionRuleMap);
        } catch (Exception e) {
            logger.error("decisionRule delete failed, id: {}, exception : {}", id, LoggerUtils.getStackTrace(e));
            decisionRuleMap = obtainErrorResult(e.getMessage());
        }
        return JacksonUtils.toJson(decisionRuleMap);
    }

    static Map<String, Object> obtainSuccessResult(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("data", data);
        return result;
    }

    static Map<String, Object> obtainErrorResult(String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 1);
        result.put("msg", msg);
        return result;
    }

    private  Map<String, Object> obtainSearchData(DecisionRule decisionRule, int pageNum, int pageSize) {
        Page page = decisionRuleRepo.selectExampleByPage(decisionRule, pageNum, pageSize);
        Map<String, Object> searchData = new HashMap<>();
        searchData.put("total", page.getTotal());
        searchData.put("list", page);
        return searchData;
    }
}
