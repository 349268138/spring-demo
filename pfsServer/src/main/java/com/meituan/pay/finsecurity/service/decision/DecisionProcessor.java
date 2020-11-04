package com.meituan.pay.finsecurity.service.decision;

import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.enums.ProcessResultEnum;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */
public interface DecisionProcessor {
    ProcessResultEnum decide(EventRule eventRule, DecisionRule decisionRule, String dataJson);
}
