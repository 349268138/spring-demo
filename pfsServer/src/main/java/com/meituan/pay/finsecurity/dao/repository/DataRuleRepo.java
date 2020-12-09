package com.meituan.pay.finsecurity.dao.repository;

import com.meituan.pay.finsecurity.dao.mapper.DataRuleMapper;
import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.pay.finsecurity.po.DataRuleExample;
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
public class DataRuleRepo {

    @Autowired
    private DataRuleMapper dataRuleMapper;

    public List<DataRule> selectByEventId(Long eventId) {
        DataRuleExample example = new DataRuleExample();
        example.createCriteria().andEventIdEqualTo(eventId).andStatusEqualTo(StatusEnum.ON);
        return dataRuleMapper.selectByExample(example);
    }
}
