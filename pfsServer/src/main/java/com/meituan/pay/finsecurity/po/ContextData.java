package com.meituan.pay.finsecurity.po;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/11/2.
 */
public class ContextData {
    String eventData;
    String tradeData;

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    public String getTradeData() {
        return tradeData;
    }

    public void setTradeData(String tradeData) {
        this.tradeData = tradeData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContextData)) return false;

        ContextData that = (ContextData) o;

        if (eventData != null ? !eventData.equals(that.eventData) : that.eventData != null) return false;
        return tradeData != null ? tradeData.equals(that.tradeData) : that.tradeData == null;
    }

    @Override
    public int hashCode() {
        int result = eventData != null ? eventData.hashCode() : 0;
        result = 31 * result + (tradeData != null ? tradeData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContextData{");
        sb.append("eventData='").append(eventData).append('\'');
        sb.append(", tradeData='").append(tradeData).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
