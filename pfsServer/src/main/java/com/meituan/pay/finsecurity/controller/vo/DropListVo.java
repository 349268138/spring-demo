package com.meituan.pay.finsecurity.controller.vo;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */
public class DropListVo {
    private String lable;
    private Long value;

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DropListVo)) return false;

        DropListVo that = (DropListVo) o;

        if (lable != null ? !lable.equals(that.lable) : that.lable != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = lable != null ? lable.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DropListVo{");
        sb.append("lable='").append(lable).append('\'');
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}