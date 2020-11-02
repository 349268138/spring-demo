package com.meituan.pay.finsecurity.service.event;

import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.pay.finsecurity.po.DicisionRule;
import com.meituan.pay.finsecurity.po.TradeEvent;
import com.meituan.pay.finsecurity.po.enums.ResultEnum;
import com.meituan.pay.finsecurity.service.data.DataQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.List;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/30.
 */
@Scope("prototype")
public class EventProcessor {
    private TradeEvent tradeEvent;
    private List<DataRule> dataRuleList;
    private List<DicisionRule> dicisionRuleList;

    @Autowired
    private DataQueryService dataQueryService;

    public ResultEnum process(String eventData) {
        String tradeData = dataQueryService.queryTradeData(dataRuleList, eventData);
        for (DicisionRule dicisionRule : dicisionRuleList) {
            if (handleDicisionRule(dicisionRule) == ResultEnum.INTERCEPT) {
                return ResultEnum.INTERCEPT;
            }
        }
        return null;
    }

    private ResultEnum handleDicisionRule(DicisionRule dicisionRule) {
        return ResultEnum.INTERCEPT;
    }

//    private ContextData obtainContextData() {
//
//    }

    public TradeEvent getTradeEvent() {
        return tradeEvent;
    }

    public void setTradeEvent(TradeEvent tradeEvent) {
        this.tradeEvent = tradeEvent;
    }

    public List<DataRule> getDataRuleList() {
        return dataRuleList;
    }

    public void setDataRuleList(List<DataRule> dataRuleList) {
        this.dataRuleList = dataRuleList;
    }

    public List<DicisionRule> getDicisionRuleList() {
        return dicisionRuleList;
    }

    public void setDicisionRuleList(List<DicisionRule> dicisionRuleList) {
        this.dicisionRuleList = dicisionRuleList;
    }

}
