package com.springboot.bet.typehandler;

import com.springboot.bet.enumeration.BetType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Bet Type Handler - Transformation between database type and java type
 */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(value = BetType.class)
public class BetTypeHandler extends BaseTypeHandler<BetType> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, BetType betType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, betType.getId());
    }

    /**
     * Get result by using column name
     *
     * @param resultSet
     * @param columnName
     * @return
     * @throws SQLException
     */
    @Override
    public BetType getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return BetType.getTypeById(resultSet.getInt(columnName));
    }

    /**
     * Get result by using column index
     *
     * @param resultSet
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public BetType getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return BetType.getTypeById(resultSet.getInt(columnIndex));
    }

    /**
     * Get result by using column index - Using callback
     *
     * @param callableStatement
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public BetType getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return BetType.getTypeById(callableStatement.getInt(columnIndex));
    }
}
