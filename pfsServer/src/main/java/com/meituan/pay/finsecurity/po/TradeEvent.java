package com.meituan.pay.finsecurity.po;

import java.util.List;

/**
 * @author hhhb
 * @date 2020/11/3 2:32 下午
 */
public class TradeEvent {
    private EventRule eventRule;
    private List<DataRule> dataRuleList;
    private List<DecisionRule> decisionRuleList;

    public EventRule getEventRule() {
        return eventRule;
    }

    public void setEventRule(EventRule eventRule) {
        this.eventRule = eventRule;
    }

    public List<DataRule> getDataRuleList() {
        return dataRuleList;
    }

    public void setDataRuleList(List<DataRule> dataRuleList) {
        this.dataRuleList = dataRuleList;
    }

    public List<DecisionRule> getDecisionRuleList() {
        return decisionRuleList;
    }

    public void setDecisionRuleList(List<DecisionRule> decisionRuleList) {
        this.decisionRuleList = decisionRuleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TradeEvent)) return false;

        TradeEvent that = (TradeEvent) o;

        if (eventRule != null ? !eventRule.equals(that.eventRule) : that.eventRule != null) return false;
        if (dataRuleList != null ? !dataRuleList.equals(that.dataRuleList) : that.dataRuleList != null) return false;
        return decisionRuleList != null ? decisionRuleList.equals(that.decisionRuleList) : that.decisionRuleList == null;
    }

    @Override
    public int hashCode() {
        int result = eventRule != null ? eventRule.hashCode() : 0;
        result = 31 * result + (dataRuleList != null ? dataRuleList.hashCode() : 0);
        result = 31 * result + (decisionRuleList != null ? decisionRuleList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TradeEvent{");
        sb.append("eventRule=").append(eventRule);
        sb.append(", dataRuleList=").append(dataRuleList);
        sb.append(", decisionRuleList=").append(decisionRuleList);
        sb.append('}');
        return sb.toString();
    }
}
