package com.meituan.pay.finsecurity.dao.repository;

import com.meituan.pay.finsecurity.po.EventRule;

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
}
