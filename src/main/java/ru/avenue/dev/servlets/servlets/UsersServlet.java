package ru.avenue.dev.servlets.servlets;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.avenue.dev.servlets.entities.User;
import ru.avenue.dev.servlets.repositories.realisations.UsersRepositoryJdbcImpl;
import ru.avenue.dev.servlets.services.realisations.UsersServiceImpl;
import ru.avenue.dev.servlets.services.templates.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/students");
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername("postgres");
        config.setPassword("qwerty007");
        config.setMaximumPoolSize(10);
        HikariDataSource source = new HikariDataSource(config);
        this.usersService = new UsersServiceImpl(new UsersRepositoryJdbcImpl(source));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = usersService.getAll();
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/users_page.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
