package com.meituan.pay.finsecurity.service.event;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.pay.finsecurity.constant.ScriptConstant;
import com.meituan.pay.finsecurity.po.ContextData;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.enums.EventResultEnum;
import com.meituan.pay.finsecurity.po.enums.TypeEnum;
import com.meituan.pay.finsecurity.script.GroovyScript;
import org.springframework.stereotype.Service;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/30.
 */
@Service
public class EventProcessor {

    public EventResultEnum process(ContextData contextData) {
        String dataJson = JacksonUtils.toJson(contextData);
        for (DecisionRule decisionRule : decisionRuleList) {
            if (handleDecisionRule(decisionRule, dataJson) == EventResultEnum.INTERCEPT) {
                return EventResultEnum.INTERCEPT;
            }
        }
        return null;
    }

    private EventResultEnum handleDecisionRule(DecisionRule decisionRule, String dataJson) {
        if (TypeEnum.INTERCEPT == decisionRule.getType()) {
            return processInterceptType(dataJson, decisionRule);
        }

        return processDefaultType(decisionRule, dataJson);
    }

    private EventResultEnum processInterceptType(String dataJson, DecisionRule decisionRule) {
        boolean result = (boolean) GroovyScript.script(ScriptConstant.CONTEXT_DATA, dataJson, decisionRule.getExpr());
        return result ? EventResultEnum.PASS : EventResultEnum.INTERCEPT;
    }

    private EventResultEnum processDefaultType(DecisionRule decisionRule, String dataJson) {

        return EventResultEnum.PASS;
    }
}
