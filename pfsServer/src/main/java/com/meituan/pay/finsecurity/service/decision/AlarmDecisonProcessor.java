package com.meituan.pay.finsecurity.service.decision;

import com.meituan.pay.finsecurity.constant.ScriptConstant;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.enums.ProcessResultEnum;
import com.meituan.pay.finsecurity.script.GroovyScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */
@Service
public class AlarmDecisonProcessor implements DecisionProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmDecisonProcessor.class);

    @Override
    public ProcessResultEnum decide(EventRule eventRule, DecisionRule decisionRule, String dataJson) {
        boolean result = (boolean) GroovyScript.script(ScriptConstant.CONTEXT_DATA, dataJson, decisionRule.getExpr());
        if (!result) {
            LOGGER.error("funds security alarm. eventCode: {}, decisionName: {}, dataJson: {}", eventRule.getCode(), decisionRule.getName(), dataJson);
        }
        return ProcessResultEnum.PASS;
    }
}
