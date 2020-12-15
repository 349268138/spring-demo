package com.meituan.pay.finsecurity.dao.repository;

import com.meituan.pay.finsecurity.dao.mapper.EventRuleMapper;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.EventRuleExample;
import com.meituan.pay.finsecurity.po.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/30.
 */
@Component
public class EventRuleRepo {

    @Autowired
    private EventRuleMapper eventRuleMapper;

    public List<EventRule> selectByOnStatus() {
        EventRuleExample example = new EventRuleExample();
        example.createCriteria().andStatusEqualTo(StatusEnum.ON);
        return eventRuleMapper.selectByExample(example);
    }

    public List<EventRule> selectAll() {
        EventRuleExample example = new EventRuleExample();
        return eventRuleMapper.selectByExample(example);
    }
}
