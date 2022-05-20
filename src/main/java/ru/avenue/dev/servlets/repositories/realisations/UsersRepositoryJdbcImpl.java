package ru.avenue.dev.servlets.repositories.realisations;

import ru.avenue.dev.servlets.entities.User;
import ru.avenue.dev.servlets.repositories.templates.UsersRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//dao - data access object
public class UsersRepositoryJdbcImpl implements UsersRepository {

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/students", "postgres", "qwerty007");
            statement = connection.createStatement();
            List<User> result = new ArrayList<>();
            String sql = "SELECT * FROM account";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User current = new User();
                current.setId(resultSet.getLong("id"));
                current.setCreateTime(resultSet.getTimestamp("create_time"));
                result.add(current);
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
        return null;
    }

    private void stupidMethod() {
        System.out.println("hello!");
    }
}
