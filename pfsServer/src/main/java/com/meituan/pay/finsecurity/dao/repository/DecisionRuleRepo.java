package com.meituan.pay.finsecurity.dao.repository;

import com.meituan.pay.finsecurity.po.DecisionRule;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/30.
 */
@Repository
public class DecisionRuleRepo {

    public List<DecisionRule> selectByEventId(Long eventId) {
        List<DecisionRule> decisionRuleList = new ArrayList<>();
        return decisionRuleList;
    }
}
