package com.meituan.pay.finsecurity.po;

import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import com.meituan.pay.finsecurity.po.enums.StatusEnum;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */
public class DataRule1 {
    private Long id;
    private Long eventId;
    private String name;
    private String alias;
    private DataAccessTypeEnum type;
    private String address;
    private String keyExpr;
    private StatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public DataAccessTypeEnum getType() {
        return type;
    }

    public void setType(DataAccessTypeEnum type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKeyExpr() {
        return keyExpr;
    }

    public void setKeyExpr(String keyExpr) {
        this.keyExpr = keyExpr;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataRule1)) return false;

        DataRule1 dataRule = (DataRule1) o;

        if (id != null ? !id.equals(dataRule.id) : dataRule.id != null) return false;
        if (eventId != null ? !eventId.equals(dataRule.eventId) : dataRule.eventId != null) return false;
        if (name != null ? !name.equals(dataRule.name) : dataRule.name != null) return false;
        if (alias != null ? !alias.equals(dataRule.alias) : dataRule.alias != null) return false;
        if (type != dataRule.type) return false;
        if (address != null ? !address.equals(dataRule.address) : dataRule.address != null) return false;
        if (keyExpr != null ? !keyExpr.equals(dataRule.keyExpr) : dataRule.keyExpr != null) return false;
        return status == dataRule.status;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (keyExpr != null ? keyExpr.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DataRule{");
        sb.append("id=").append(id);
        sb.append(", eventId=").append(eventId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", alias='").append(alias).append('\'');
        sb.append(", type=").append(type);
        sb.append(", address='").append(address).append('\'');
        sb.append(", keyExpr='").append(keyExpr).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}