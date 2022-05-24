package ru.avenue.dev.servlets.servlets;

import ru.avenue.dev.servlets.entities.User;
import ru.avenue.dev.servlets.repositories.templates.UsersRepository;
import ru.avenue.dev.servlets.services.realisations.UsersServiceImpl;
import ru.avenue.dev.servlets.services.templates.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        this.usersService = new UsersServiceImpl((UsersRepository) context.getAttribute("usersRepository"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = usersService.getAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/users_page.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
