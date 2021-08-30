package me.cuiyijie.joyea.config;

import com.alibaba.druid.util.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author:
 * @Date: 2021/8/30 15:39
 */
@MappedTypes(value = {UserFileType.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    private Class<E> type;

    public EnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("type argument can not be null");
        } else {
            this.type = type;
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            preparedStatement.setString(i, e.name());
        } else {
            preparedStatement.setObject(i, e.name(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return get(resultSet.getString(columnName));
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return get(resultSet.getString(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return get(callableStatement.getString(columnIndex));
    }

    private E get(String value) {
        if (value == null) return null;
        if (StringUtils.isNumber(value)) {
            return get(type, Integer.parseInt(value));
        } else {
            return Enum.valueOf(type, value);
        }
    }

    private E get(Class<E> type, Integer v) {
        Method method = null;
        E result = null;
        try {
            method = type.getMethod("get", int.class);
            result = (E) method.invoke(type, v);
        } catch (NoSuchMethodException exception) {
            exception.printStackTrace();
            result = Enum.valueOf(type, String.valueOf(v));
        } catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
        return result;
    }
}
