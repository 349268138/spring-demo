package com.meituan.pay.finsecurity.dao.typehandler;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.pay.finsecurity.po.Vector;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedTypes({Vector.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class ListHandler<E extends Object> extends BaseTypeHandler<List<E>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<E> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter == null ? null : JacksonUtils.toJson(parameter));
    }

    @Override
    public List<E> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return resolveResult(rs.getString(columnName));
    }

    @Override
    public List<E> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return resolveResult(rs.getString(columnIndex));
    }

    @Override
    public List<E> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return resolveResult(cs.getString(columnIndex));
    }

    private List<E> resolveResult(String value) {
        return value == null ? null : (List<E>) JacksonUtils.jsonToBeanList(value, Vector.class);
    }
}