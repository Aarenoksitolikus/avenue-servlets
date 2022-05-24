package ru.avenue.dev.servlets.repositories.realisations;

import ru.avenue.dev.servlets.repositories.templates.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SimpleJdbcTemplate {
    DataSource dataSource;

    public SimpleJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object ... args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            List<T> result = new ArrayList<>();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                //ignore
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                //ignore
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                //ignore
            }
        }
    }
}
