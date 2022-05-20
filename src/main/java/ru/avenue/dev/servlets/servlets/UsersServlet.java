package ru.avenue.dev.servlets.servlets;

import ru.avenue.dev.servlets.entities.User;
import ru.avenue.dev.servlets.repositories.realisations.UsersRepositoryJdbcImpl;
import ru.avenue.dev.servlets.services.realisations.UsersServiceImpl;
import ru.avenue.dev.servlets.services.templates.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        this.usersService = new UsersServiceImpl(new UsersRepositoryJdbcImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = usersService.getAll();
        System.out.println(users.get(1).getId());
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/users_page.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
