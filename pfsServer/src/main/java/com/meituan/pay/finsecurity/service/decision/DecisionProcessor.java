package com.meituan.pay.finsecurity.service.decision;

import com.dianping.cat.util.MetricHelper;
import com.meituan.pay.finsecurity.constant.ScriptConstant;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.Vector;
import com.meituan.pay.finsecurity.po.enums.ProcessResultEnum;
import com.meituan.pay.finsecurity.script.GroovyScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */
public interface DecisionProcessor {
    public static final Logger logger = LoggerFactory.getLogger(DecisionProcessor.class);

    ProcessResultEnum decide(EventRule eventRule, DecisionRule decisionRule, String dataJson);

    default MetricHelper buildMetricHelper(EventRule eventRule, String dataJson) {
        if (CollectionUtils.isEmpty(eventRule.getVectorList())) {
            logger.error("eventRule vectorList not config. eventCode: {}", eventRule.getCode());
            return null;
        }

        MetricHelper pHelper = MetricHelper.build();
        for (Vector vector : eventRule.getVectorList()) {
            String value = String.valueOf(GroovyScript.script(ScriptConstant.CONTEXT_DATA, dataJson, vector.getExpr()));
            if(ScriptConstant.NULL.equals(value)) {
                continue;
            }
            pHelper.tag(vector.getAlias(), value);
        }
        return pHelper;
    }
}
