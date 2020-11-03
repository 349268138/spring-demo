package com.meituan.pay.finsecurity.dao.repository;

import com.meituan.pay.finsecurity.po.DecisionRule;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/30.
 */
public class DecisionRepo {

    public List<DecisionRule> selectByEventId(Long eventId) {
        List<DecisionRule> decisionRuleList = new ArrayList<>();
        return decisionRuleList;
    }
}
