package com.meituan.pay.finsecurity.dao.mapper;

import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.EventRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EventRuleMapper {
    int countByExample(EventRuleExample example);

    int deleteByExample(EventRuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EventRule record);

    int insertSelective(EventRule record);

    List<EventRule> selectByExample(EventRuleExample example);

    EventRule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EventRule record, @Param("example") EventRuleExample example);

    int updateByExample(@Param("record") EventRule record, @Param("example") EventRuleExample example);

    int updateByPrimaryKeySelective(EventRule record);

    int updateByPrimaryKey(EventRule record);
}