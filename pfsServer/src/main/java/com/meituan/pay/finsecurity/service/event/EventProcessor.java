package com.meituan.pay.finsecurity.service.event;

import com.meituan.pay.finsecurity.po.ContextData;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.enums.ProcessResultEnum;
import org.springframework.stereotype.Service;

/**
 * @author hhhb
 * @date 2020/11/2 5:17 下午
 */
@Service
public class EventProcessor {

    public ProcessResultEnum process(EventRule eventRule, ContextData contextData) {
//        String dataJson = JacksonUtils.toJson(contextData);
//        for (DecisionRule decisionRule : decisionRuleList) {
//            if (handleDecisionRule(decisionRule, dataJson) == EventResultEnum.INTERCEPT) {
//                return EventResultEnum.INTERCEPT;
//            }
//        }
        return ProcessResultEnum.PASS;
    }

//    private EventResultEnum handleDecisionRule(DecisionRule decisionRule, String dataJson) {
//        if (TypeEnum.INTERCEPT == decisionRule.getType()) {
//            return processInterceptType(dataJson, decisionRule);
//        }
//
//        return processDefaultType(decisionRule, dataJson);
//    }
//
//    private EventResultEnum processInterceptType(String dataJson, DecisionRule decisionRule) {
//        boolean result = (boolean) GroovyScript.script(ScriptConstant.CONTEXT_DATA, dataJson, decisionRule.getExpr());
//        return result ? EventResultEnum.PASS : EventResultEnum.INTERCEPT;
//    }
//
//    private EventResultEnum processDefaultType(DecisionRule decisionRule, String dataJson) {
//
//        return EventResultEnum.PASS;
//    }
}
