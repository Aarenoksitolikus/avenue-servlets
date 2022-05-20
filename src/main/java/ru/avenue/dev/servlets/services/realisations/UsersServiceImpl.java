package ru.avenue.dev.servlets.services.realisations;

import ru.avenue.dev.servlets.entities.User;
import ru.avenue.dev.servlets.repositories.templates.UsersRepository;
import ru.avenue.dev.servlets.services.templates.UsersService;

import java.sql.SQLException;
import java.util.List;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public User getById() {
        return null;
    }

    @Override
    public User createNewUser() {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }

    @Override
    public void deleteUser(User user) {

    }
}
