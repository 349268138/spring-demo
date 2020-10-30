package com.meituan.pay.finsecurity.dao.repository;

import com.meituan.pay.finsecurity.po.TradeEvent;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/30.
 */
public class TradeEventRepo {

    public TradeEvent selectByCode(String code) {
        return new TradeEvent();
    };
}
