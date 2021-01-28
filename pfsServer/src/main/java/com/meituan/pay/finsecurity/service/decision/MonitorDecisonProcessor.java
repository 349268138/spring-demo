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
public class MonitorDecisonProcessor implements DecisionProcessor {

    @Override
    public ProcessResultEnum decide(EventRule eventRule, DecisionRule decisionRule, String dataJson) {
        int count = (int) GroovyScript.script(ScriptConstant.CONTEXT_DATA, dataJson, decisionRule.getExpr());
        if (count > 0) {
            monitor(eventRule, decisionRule, dataJson, count);
        }
        return ProcessResultEnum.PASS;
    }

    private void monitor(EventRule eventRule, DecisionRule decisionRule, String dataJson, int count) {
        MetricHelper pHelper = buildMetricHelper(eventRule, dataJson);
        if (Objects.isNull(pHelper)) {
            return;
        }

        pHelper.name(decisionRule.getAlias()).count(count);
    }
}
