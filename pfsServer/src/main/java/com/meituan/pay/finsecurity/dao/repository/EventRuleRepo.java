package com.meituan.pay.finsecurity.dao.repository;

import com.meituan.pay.finsecurity.po.EventRule;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/30.
 */
@Repository
public class EventRuleRepo {

    public List<EventRule> selectByOnStatus() {
        List<EventRule> eventRuleList = new ArrayList<>();
        return eventRuleList;
    }
}
