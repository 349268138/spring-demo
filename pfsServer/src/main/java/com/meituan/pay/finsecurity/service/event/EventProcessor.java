package com.meituan.pay.finsecurity.service.event;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.pay.finsecurity.po.ContextData;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.TradeEvent;
import com.meituan.pay.finsecurity.po.enums.ProcessResultEnum;
import com.meituan.pay.finsecurity.service.decision.DecisionProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author hhhb
 * @date 2020/11/2 5:17 下午
 */
@Service
public class EventProcessor {
    @Autowired
    private DecisionProcessorFactory decisionProcessorFactory;

    public ProcessResultEnum process(TradeEvent tradeEvent, ContextData contextData) {
        String dataJson = JacksonUtils.toJson(contextData);
        if (CollectionUtils.isEmpty(tradeEvent.getDecisionRuleList())) {
            return ProcessResultEnum.PASS;
        }

        for (DecisionRule decisionRule : tradeEvent.getDecisionRuleList()) {
            ProcessResultEnum result = decisionProcessorFactory.obtainProcessor(decisionRule.getType()).decide(tradeEvent.getEventRule(), decisionRule, dataJson);
            if (result == ProcessResultEnum.INTERCEPT) {
                return ProcessResultEnum.INTERCEPT;
            }
        }
        return ProcessResultEnum.PASS;
    }
}
