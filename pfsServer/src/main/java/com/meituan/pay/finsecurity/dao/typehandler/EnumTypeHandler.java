package com.meituan.pay.finsecurity.dao.typehandler;

import com.meituan.pay.finsecurity.po.enums.CodeEnum;
import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import com.meituan.pay.finsecurity.po.enums.StatusEnum;
import com.meituan.pay.finsecurity.po.enums.TypeEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({StatusEnum.class, DataAccessTypeEnum.class, TypeEnum.class})
@MappedJdbcTypes({JdbcType.INTEGER, JdbcType.SMALLINT, })
public class EnumTypeHandler<E extends Enum<E> & CodeEnum> extends BaseTypeHandler<E> {

    private Class<E> typeClass;

    public EnumTypeHandler(Class<E> typeClass) {
        if (typeClass == null) throw new IllegalArgumentException("Type class argument cannot be null");
        this.typeClass = typeClass;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return resolveResult(rs.getInt(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return resolveResult(rs.getInt(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return resolveResult(cs.getInt(columnIndex));
    }

    private E resolveResult(int value) {
        try {
            Method method = typeClass.getMethod("findValue", int.class);
            method.setAccessible(true);
            return (E) method.invoke(null, value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new EnumException(e.getMessage(), e);
        }
    }
}