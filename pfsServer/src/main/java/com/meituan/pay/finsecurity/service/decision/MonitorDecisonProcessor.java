package com.meituan.pay.finsecurity.service.decision;

import com.dianping.cat.util.MetricHelper;
import com.meituan.pay.finsecurity.constant.ScriptConstant;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.Vector;
import com.meituan.pay.finsecurity.po.enums.ProcessResultEnum;
import com.meituan.pay.finsecurity.script.GroovyScript;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        MetricHelper pHelper = MetricHelper.build();
        for (Vector vector : eventRule.getVectorList()) {
            String value = String.valueOf(GroovyScript.script(dataJson, ScriptConstant.CONTEXT_DATA, vector.getExpr()));
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            pHelper.tag(vector.getAlias(), value);
        }
        pHelper.name(decisionRule.getAlias()).count(count);
    }
}
