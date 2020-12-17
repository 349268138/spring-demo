package com.meituan.pay.finsecurity.controller;

import com.github.pagehelper.Page;
import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.controller.vo.DropListVo;
import com.meituan.pay.finsecurity.dao.repository.DecisionRuleRepo;
import com.meituan.pay.finsecurity.dao.repository.EventRuleRepo;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.EventRule;
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
            decisionRuleMap = obtainErrorResult(e.getMessage());
            logger.error("decisionRule query failed, decisionRule: {}, pageNum: {}, pageSize: {} : {}, exception: {}", decisionRule, pageNum, pageSize, LoggerUtils.getStackTrace(e));
        }
        return JacksonUtils.toJson(decisionRuleMap);

    }

    @RequestMapping(value = "decision-add")
    public String decisionAdd(DecisionRule decisionRule){
        Map<String, Object> decisionRuleMap = new HashMap<>();
        try{
            decisionRuleRepo.insertBySelective(decisionRule);
            decisionRuleMap = obtainSuccessResult(decisionRule.getId());
            logger.info("decisionRule add succeeded, decisionRule: {}, decisionRuleMap: {}", decisionRule, decisionRuleMap);
        } catch (Exception e) {
            decisionRuleMap = obtainErrorResult(e.getMessage());
            logger.error("decisionRule add failed, decisionRule: {}, exception: {}", decisionRule, LoggerUtils.getStackTrace(e));
        }
        return JacksonUtils.toJson(decisionRuleMap);
    }

    @RequestMapping(value = "decision-update")
    public String decisionUpdate(DecisionRule decisionRule){
        Map<String, Object> decisionRuleMap = new HashMap<>();
        try{
            decisionRuleRepo.updateByIdSelective(decisionRule);
            decisionRuleMap = obtainSuccessResult(decisionRule.getId());
            logger.info("decisionRule update succeeded, decisionRule: {}, decisionRuleMap: {}", decisionRule, decisionRuleMap);
        } catch (Exception e) {
            decisionRuleMap = obtainErrorResult(e.getMessage());
            logger.error("decisionRule update failed, decisionRule: {}, exception : {}", decisionRule, LoggerUtils.getStackTrace(e));
        }
        return JacksonUtils.toJson(decisionRuleMap);
    }

    @RequestMapping(value = "decision-delete")
    public String decisionDelete(DecisionRule decisionRule){
        Map<String, Object> decisionRuleMap = new HashMap<>();
        try{
            decisionRuleRepo.deleteByPrimaryKey(decisionRule.getId());
            decisionRuleMap = obtainSuccessResult(decisionRule.getId());
            logger.info("decisionRule delete succeeded, decisionRule: {}, decisionRuleMap: {}", decisionRule, decisionRuleMap);
        } catch (Exception e) {
            decisionRuleMap = obtainErrorResult(e.getMessage());
            logger.error("decisionRule delete failed, decisionRule: {}, exception : {}", decisionRule, LoggerUtils.getStackTrace(e));
        }
        return JacksonUtils.toJson(decisionRuleMap);
    }

    @RequestMapping(value = "event-search-all-code")
    public String eventSearchAllCode() {
        Map<String, Object> eventRuleMap = new HashMap<>();
        try{
            eventRuleMap = obtainSuccessResult(obtainDropListVos());
            logger.info("event search all code succeeded, eventRuleMap: {}", eventRuleMap);
        } catch (Exception e) {
            eventRuleMap = obtainErrorResult(e.getMessage());
            logger.error("event search all code failed, exception : {}", LoggerUtils.getStackTrace(e));
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

    private List<DropListVo> obtainDropListVos() {
        List<EventRule> eventRuleList = eventRuleRepo.selectAll();
        List<DropListVo> dropListVos = new ArrayList<>();
        for (EventRule eventRule : eventRuleList) {
            DropListVo dropListVo = new DropListVo();
            dropListVo.setValue(eventRule.getCode());
            dropListVo.setLable(eventRule.getId());
            dropListVos.add(dropListVo);
        }
        return dropListVos;
    }

    private Map<String, Object> obtainSearchData(DecisionRule decisionRule, int pageNum, int pageSize) {
        Page page = decisionRuleRepo.selectExampleByPage(decisionRule, pageNum, pageSize);
        Map<String, Object> searchData = new HashMap<>();
        searchData.put("total", page.getTotal());
        searchData.put("list", page);
        return searchData;
    }
}
