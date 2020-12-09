package com.meituan.pay.finsecurity.po;

import com.meituan.pay.finsecurity.po.enums.StatusEnum;

import java.util.List;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */
public class EventRule1 {
    private Long id;
    private String code;
    private String name;
    private List<Vector> vectorList;
    private String extendedData;
    private StatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vector> getVectorList() {
        return vectorList;
    }

    public void setVectorList(List<Vector> vectorList) {
        this.vectorList = vectorList;
    }

    public String getExtendedData() {
        return extendedData;
    }

    public void setExtendedData(String extendedData) {
        this.extendedData = extendedData;
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
        if (!(o instanceof EventRule1)) return false;

        EventRule1 eventRule = (EventRule1) o;

        if (id != null ? !id.equals(eventRule.id) : eventRule.id != null) return false;
        if (code != null ? !code.equals(eventRule.code) : eventRule.code != null) return false;
        if (name != null ? !name.equals(eventRule.name) : eventRule.name != null) return false;
        if (vectorList != null ? !vectorList.equals(eventRule.vectorList) : eventRule.vectorList != null) return false;
        if (extendedData != null ? !extendedData.equals(eventRule.extendedData) : eventRule.extendedData != null)
            return false;
        return status == eventRule.status;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (vectorList != null ? vectorList.hashCode() : 0);
        result = 31 * result + (extendedData != null ? extendedData.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EventRule{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", vectorList=").append(vectorList);
        sb.append(", extendedData='").append(extendedData).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}