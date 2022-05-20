package ru.avenue.dev.servlets.repositories.templates;

import ru.avenue.dev.servlets.entities.User;

import java.sql.Timestamp;
import java.util.List;

//этот репозиторий будет служить только для работы с сущностями пользователей в БД
public interface UsersRepository extends CrudRepository<User> {
    List<User> findAllByCreateTime(Timestamp timestamp);
}
