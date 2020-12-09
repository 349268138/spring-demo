package com.meituan.pay.finsecurity.dao.mapper;

import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.DecisionRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DecisionRuleMapper {
    int countByExample(DecisionRuleExample example);

    int deleteByExample(DecisionRuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DecisionRule record);

    int insertSelective(DecisionRule record);

    List<DecisionRule> selectByExample(DecisionRuleExample example);

    DecisionRule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DecisionRule record, @Param("example") DecisionRuleExample example);

    int updateByExample(@Param("record") DecisionRule record, @Param("example") DecisionRuleExample example);

    int updateByPrimaryKeySelective(DecisionRule record);

    int updateByPrimaryKey(DecisionRule record);
}