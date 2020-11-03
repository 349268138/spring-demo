package com.meituan.pay.finsecurity.service.event;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.pay.finsecurity.constant.ScriptConstant;
import com.meituan.pay.finsecurity.po.ContextData;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.TradeEvent1;
import com.meituan.pay.finsecurity.po.enums.ProcessResultEnum;
import com.meituan.pay.finsecurity.po.enums.TypeEnum;
import com.meituan.pay.finsecurity.script.GroovyScript;
import org.springframework.stereotype.Service;

/**
 * @author hhhb
 * @date 2020/11/2 5:17 下午
 */
@Service
public class EventProcessor {

    public ProcessResultEnum process(TradeEvent1 eventRule, ContextData contextData) {
        String dataJson = JacksonUtils.toJson(contextData);
        for (DecisionRule decisionRule : eventRule.getDecisionRuleList()) {
            if (handleDecisionRule(decisionRule, dataJson) == ProcessResultEnum.INTERCEPT) {
                return ProcessResultEnum.INTERCEPT;
            }
        }
        return ProcessResultEnum.PASS;
    }

    private ProcessResultEnum handleDecisionRule(DecisionRule decisionRule, String dataJson) {
        if (TypeEnum.INTERCEPT == decisionRule.getType()) {
            return processInterceptType(dataJson, decisionRule);
        }

        return processDefaultType(decisionRule, dataJson);
    }

    private ProcessResultEnum processInterceptType(String dataJson, DecisionRule decisionRule) {
        boolean result = (boolean) GroovyScript.script(ScriptConstant.CONTEXT_DATA, dataJson, decisionRule.getExpr());
        return result ? ProcessResultEnum.PASS : ProcessResultEnum.INTERCEPT;
    }

    private ProcessResultEnum processDefaultType(DecisionRule decisionRule, String dataJson) {

        return ProcessResultEnum.PASS;
    }
}
