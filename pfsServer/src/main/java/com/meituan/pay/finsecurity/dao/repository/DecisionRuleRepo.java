package com.meituan.pay.finsecurity.dao.repository;

import com.github.pagehelper.PageHelper;
import com.meituan.pay.finsecurity.dao.mapper.DecisionRuleMapper;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.DecisionRuleExample;
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
public class DecisionRuleRepo {

    @Autowired
    private DecisionRuleMapper decisionRuleMapper;

    public List<DecisionRule> selectByEventId(Long eventId) {
        DecisionRuleExample example = new DecisionRuleExample();
        example.createCriteria().andEventIdEqualTo(eventId);
        return decisionRuleMapper.selectByExample(example);
    }

    public List<DecisionRule> selectByEventIdAndOnStatus(Long eventId) {
        DecisionRuleExample example = new DecisionRuleExample();
        example.createCriteria().andEventIdEqualTo(eventId).andStatusEqualTo(StatusEnum.ON);
        return decisionRuleMapper.selectByExample(example);
    }

    public List<DecisionRule> getAllByPage(int pageNum, int pageSize) {
        DecisionRuleExample example = new DecisionRuleExample();
        PageHelper.startPage(pageNum, pageSize, false, true);
        return decisionRuleMapper.selectByExample(example);
    }

    public int insertBySelective(DecisionRule decisionRule) {
        return decisionRuleMapper.insertSelective(decisionRule);
    }

    public int updateByIdSelective(DecisionRule decisionRule) {
        return decisionRuleMapper.updateByPrimaryKeySelective(decisionRule);
    }
}
