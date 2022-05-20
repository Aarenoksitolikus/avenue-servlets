package ru.avenue.dev.servlets.repositories.templates;

import java.sql.SQLException;
import java.util.List;

//CRUD - create, read, update, delete
public interface CrudRepository<T> {

    //этот метод будет создавать новую сущность в БД и возвращать ее в качестве Java-объекта
    T create(T t);

    //этот метод вернет нам результат поиска в БД всех сущностей
    List<T> findAll();

    //этот метод вернет нам одну сущность из БД с данным id если она существует
    T findById(Long id);

    //этот метод обновит сущность в БД если она существует и вернет нам обновленный Java-класс
    T update(T t);

    //этот метод удалит сущность из БД если она существует
    void delete(T t);
}
