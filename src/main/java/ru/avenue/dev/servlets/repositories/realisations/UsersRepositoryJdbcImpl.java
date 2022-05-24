package ru.avenue.dev.servlets.repositories.realisations;

import ru.avenue.dev.servlets.entities.User;
import ru.avenue.dev.servlets.repositories.templates.RowMapper;
import ru.avenue.dev.servlets.repositories.templates.UsersRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

//dao - data access object
public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final SimpleJdbcTemplate template;

    RowMapper<User> rowMapper = row -> {
        User current = new User();
        current.setId(row.getLong("id"));
        current.setCreateTime(row.getTimestamp("create_time"));
        return current;
    };

    //language=SQL
    private final String QUERY_SELECT_ALL = "SELECT * FROM account";

    //language=SQL
    private final String QUERY_SELECT_ALL_BY_CREATE_TIME = "SELECT * FROM account WHERE create_time = ?";

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return template.query(QUERY_SELECT_ALL, rowMapper);
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {
    }

    @Override
    public List<User> findAllByCreateTime(Timestamp timestamp) {
        return template.query(QUERY_SELECT_ALL_BY_CREATE_TIME, rowMapper, timestamp);
    }
}
