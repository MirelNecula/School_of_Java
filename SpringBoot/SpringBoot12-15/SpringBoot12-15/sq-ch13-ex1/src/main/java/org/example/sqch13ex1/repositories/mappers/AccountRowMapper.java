package org.example.sqch13ex1.repositories.mappers;

import org.example.sqch13ex1.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int i )
        throws SQLException {
        Account a = new Account();
        a.setId(resultSet.getLong("id"));
        a.setUsername(resultSet.getString("username"));
        a.setAmount(resultSet.getBigDecimal("amount"));
        return a;
    }
}
