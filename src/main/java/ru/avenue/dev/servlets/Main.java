package ru.avenue.dev.servlets;

import ru.avenue.dev.servlets.entities.User;
import ru.avenue.dev.servlets.repositories.realisations.UsersRepositoryJdbcImpl;
import ru.avenue.dev.servlets.repositories.templates.UsersRepository;
import ru.avenue.dev.servlets.services.realisations.UsersServiceImpl;
import ru.avenue.dev.servlets.services.templates.UsersService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UsersService service = new UsersServiceImpl(new UsersRepositoryJdbcImpl());
        List<User> users = service.getAll();
        for (User current : users) {
            System.out.println(current);
        }
    }
}
