package com.meituan.pay.finsecurity.controller;

import com.dianping.cat.util.MetricHelper;
import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.pay.finsecurity.constant.ScriptConstant;
import com.meituan.pay.finsecurity.po.ContextData;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.Vector;
import com.meituan.pay.finsecurity.script.GroovyScript;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class MonitorController {
    private static final String NAME_ALARM_DECISION = "alarmDecision";
    private static final String TAG_ALIAS_DECISION = "aliasDecision";

    @RequestMapping(value = "monitor/alive")
    @ResponseBody
    public String alive() {
        EventRule eventRule = new EventRule();
        List<Vector> vectorList = new ArrayList<>();
        Vector vector = new Vector();
        vector.setAlias("partner");
        vector.setExpr("eventData.partnerid");
        vectorList.add(vector);

        Vector vector2 = new Vector();
        vector2.setAlias("status");
        vector2.setExpr("eventData.status");
        vectorList.add(vector2);

        eventRule.setVectorList(vectorList);

        ContextData contextData = new ContextData();
        contextData.setEventData("{\"status\":9,\"partnerid\":5}");
        contextData.setTradeData("");
        String dataJson = JacksonUtils.toJson(contextData);

        MetricHelper pHelper = buildMetricHelper(eventRule, dataJson);
        if (Objects.isNull(pHelper)) {
            return "ERROR";
        }

        pHelper.tag(TAG_ALIAS_DECISION, "alarmMoney");
        pHelper.name(NAME_ALARM_DECISION).count(1);

        return "OK";
    }

    MetricHelper buildMetricHelper(EventRule eventRule, String dataJson) {
        if (CollectionUtils.isEmpty(eventRule.getVectorList())) {
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
