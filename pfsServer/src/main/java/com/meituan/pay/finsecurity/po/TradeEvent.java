package com.meituan.pay.finsecurity.po;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */
public class TradeEvent {
    private Long id;
    private String code;
    private String name;
    private String vector;
    private String extendedData;

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

    public String getVector() {
        return vector;
    }

    public void setVector(String vector) {
        this.vector = vector;
    }

    public String getExtendedData() {
        return extendedData;
    }

    public void setExtendedData(String extendedData) {
        this.extendedData = extendedData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TradeEvent)) return false;

        TradeEvent that = (TradeEvent) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (vector != null ? !vector.equals(that.vector) : that.vector != null) return false;
        return extendedData != null ? extendedData.equals(that.extendedData) : that.extendedData == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (vector != null ? vector.hashCode() : 0);
        result = 31 * result + (extendedData != null ? extendedData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TradeEvent{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", vector='").append(vector).append('\'');
        sb.append(", extendedData='").append(extendedData).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
