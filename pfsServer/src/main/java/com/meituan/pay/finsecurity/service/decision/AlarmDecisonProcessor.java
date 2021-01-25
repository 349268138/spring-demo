package com.meituan.pay.finsecurity.service.decision;

import com.dianping.cat.util.MetricHelper;
import com.meituan.pay.finsecurity.constant.ScriptConstant;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.enums.ProcessResultEnum;
import com.meituan.pay.finsecurity.script.GroovyScript;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */
@Service
public class AlarmDecisonProcessor implements DecisionProcessor {
    private static final String NAME_ALARM_DECISION = "alarmDecision";
    private static final String TAG_ALIAS_DECISION = "aliasDecision";

    @Override
    public ProcessResultEnum decide(EventRule eventRule, DecisionRule decisionRule, String dataJson) {
        boolean result = (boolean) GroovyScript.script(ScriptConstant.CONTEXT_DATA, dataJson, decisionRule.getExpr());
        if (!result) {
            logger.error("funds security alarm. eventCode: {}, decisionName: {}, dataJson: {}", eventRule.getCode(), decisionRule.getName(), dataJson);
            monitor(eventRule, decisionRule, dataJson, 1);
        }
        return ProcessResultEnum.PASS;
    }

    private void monitor(EventRule eventRule, DecisionRule decisionRule, String dataJson, int count) {
        MetricHelper pHelper = buildMetricHelper(eventRule, dataJson);
        if (Objects.isNull(pHelper)) {
            return;
        }

        pHelper.tag(TAG_ALIAS_DECISION, decisionRule.getAlias());
        pHelper.name(NAME_ALARM_DECISION).count(count);
    }
}
