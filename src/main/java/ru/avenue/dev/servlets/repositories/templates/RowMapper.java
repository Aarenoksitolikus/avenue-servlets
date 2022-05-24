package ru.avenue.dev.servlets.repositories.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T mapRow(ResultSet row) throws SQLException;
}
