package com.meituan.pay.finsecurity.po;

import java.util.List;
import java.util.Objects;

/**
 * @author hhhb
 * @date 2020/11/3 2:32 下午
 */
public class EventRule {
    private TradeEvent tradeEvent;
    private List<DataRule> dataRuleList;
    private List<DicisionRule> dicisionRuleList;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventRule eventRule = (EventRule) o;
        return Objects.equals(tradeEvent, eventRule.tradeEvent) &&
                Objects.equals(dataRuleList, eventRule.dataRuleList) &&
                Objects.equals(dicisionRuleList, eventRule.dicisionRuleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeEvent, dataRuleList, dicisionRuleList);
    }
}
