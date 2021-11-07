package me.cuiyijie.joyea.enums.base;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义枚举转换器类型
 * @param <E>
 */
public class CodeEnumTypeHandler<E extends Enum<?> & BaseEnum> extends BaseTypeHandler<BaseEnum> {

    private Class<E> type;

    public CodeEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, BaseEnum baseEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, baseEnum.getKey());
    }

    @Override
    public BaseEnum getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String code = resultSet.getString(columnName);
        return resultSet.wasNull() ? null : codeOf(code);
    }

    @Override
    public BaseEnum getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String code = resultSet.getString(columnIndex);
        return resultSet.wasNull() ? null : codeOf(code);
    }

    @Override
    public BaseEnum getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String code = callableStatement.getString(columnIndex);
        return callableStatement.wasNull() ? null : codeOf(code);
    }

    private E codeOf(String code) {
        try {
            return CodeEnumUtil.codeOf(type, code);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + code + " to " + type.getSimpleName() + " by code value.", ex);
        }
    }
}
