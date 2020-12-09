package com.meituan.pay.finsecurity.dao.mapper;

import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.pay.finsecurity.po.DataRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataRuleMapper {
    int countByExample(DataRuleExample example);

    int deleteByExample(DataRuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DataRule record);

    int insertSelective(DataRule record);

    List<DataRule> selectByExample(DataRuleExample example);

    DataRule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DataRule record, @Param("example") DataRuleExample example);

    int updateByExample(@Param("record") DataRule record, @Param("example") DataRuleExample example);

    int updateByPrimaryKeySelective(DataRule record);

    int updateByPrimaryKey(DataRule record);
}