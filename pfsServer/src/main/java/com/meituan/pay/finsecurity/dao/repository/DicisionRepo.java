package com.meituan.pay.finsecurity.dao.repository;

import com.meituan.pay.finsecurity.po.DicisionRule;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/30.
 */
public class DicisionRepo {

    public List<DicisionRule> selectByEventId(Long eventId) {
        List<DicisionRule> dicisionRuleList = new ArrayList<>();
        return dicisionRuleList;
    }
}
