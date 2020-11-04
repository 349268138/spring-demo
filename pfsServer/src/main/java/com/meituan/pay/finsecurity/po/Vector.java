package com.meituan.pay.finsecurity.po;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/11/4.
 */
public class Vector {
    private String alias;
    private String name;
    private String expr;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpr() {
        return expr;
    }

    public void setExpr(String expr) {
        this.expr = expr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;

        Vector vector = (Vector) o;

        if (alias != null ? !alias.equals(vector.alias) : vector.alias != null) return false;
        if (name != null ? !name.equals(vector.name) : vector.name != null) return false;
        return expr != null ? expr.equals(vector.expr) : vector.expr == null;
    }

    @Override
    public int hashCode() {
        int result = alias != null ? alias.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (expr != null ? expr.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Vector{");
        sb.append("alias='").append(alias).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", expr='").append(expr).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
