package com.meituan.pay.finsecurity.dao.repository;

import com.meituan.pay.finsecurity.po.DataRule;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/30.
 */
@Repository
public class DataRuleRepo {

    public List<DataRule> selectByEventId(Long eventId) {
        List<DataRule> dataRuleList = new ArrayList<>();
        return dataRuleList;
    }
}
