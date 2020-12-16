package com.meituan.pay.finsecurity.dao.repository;

import com.dianping.cat.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.meituan.pay.finsecurity.dao.mapper.DecisionRuleMapper;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.DecisionRuleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/30.
 */
@Component
public class DecisionRuleRepo {

    @Autowired
    private DecisionRuleMapper decisionRuleMapper;

    public Page selectExampleByPage(DecisionRule decisionRule, int pageNum, int pageSize) {
        DecisionRuleExample example = new DecisionRuleExample();
        DecisionRuleExample.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(decisionRule.getId())) {
            criteria.andIdEqualTo(decisionRule.getId());
        }
        if (Objects.nonNull(decisionRule.getEventId())) {
            criteria.andEventIdEqualTo(decisionRule.getEventId());
        }
        if (StringUtils.isNotEmpty(decisionRule.getAlias())) {
            criteria.andAliasEqualTo(decisionRule.getAlias());
        }
        if (StringUtils.isNotEmpty(decisionRule.getName())) {
            criteria.andNameEqualTo(decisionRule.getName());
        }

        Page page = PageHelper.startPage(pageNum, pageSize);
        decisionRuleMapper.selectByExample(example);
        return page;
    }

    public int insertBySelective(DecisionRule decisionRule) {
        return decisionRuleMapper.insertSelective(decisionRule);
    }

    public int updateByIdSelective(DecisionRule decisionRule) {
        return decisionRuleMapper.updateByPrimaryKeySelective(decisionRule);
    }

    public List<DecisionRule> selectByEventId(Long eventId) {
        DecisionRuleExample example = new DecisionRuleExample();
        example.createCriteria().andEventIdEqualTo(eventId);
        return decisionRuleMapper.selectByExample(example);
    }
}
