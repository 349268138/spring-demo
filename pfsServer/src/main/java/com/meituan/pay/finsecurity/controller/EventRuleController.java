package com.meituan.pay.finsecurity.controller;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.dao.repository.EventRuleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.meituan.pay.finsecurity.controller.DecisionRuleController.obtainErrorResult;
import static com.meituan.pay.finsecurity.controller.DecisionRuleController.obtainSuccessResult;

/**
 * @author hhhb
 * @date 2020/12/23 4:39 下午
 */
@RestController
@RequestMapping(value = "api/event")
public class EventRuleController {
    Logger logger = LoggerFactory.getLogger(EventRuleController.class);

    @Autowired
    private EventRuleRepo eventRuleRepo;

    @RequestMapping(method = RequestMethod.GET, value = "search-all-code")
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

}
