package ru.avenue.dev.servlets.services.templates;

import ru.avenue.dev.servlets.entities.User;

import java.util.List;

public interface UsersService {
    List<User> getAll();
    User getById();
    User createNewUser();
    void deleteUserById(Long id);
    void deleteUser(User user);
}
