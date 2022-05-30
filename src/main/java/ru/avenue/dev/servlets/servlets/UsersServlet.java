package ru.avenue.dev.servlets.servlets;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.avenue.dev.servlets.entities.User;
import ru.avenue.dev.servlets.repositories.templates.UsersRepository;
import ru.avenue.dev.servlets.services.realisations.UsersServiceImpl;
import ru.avenue.dev.servlets.services.templates.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {

    PasswordEncoder passwordEncoder;

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        this.usersService = new UsersServiceImpl((UsersRepository) context.getAttribute("usersRepository"));
        this.passwordEncoder = (PasswordEncoder) context.getAttribute("passwordEncoder");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = usersService.getAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/users_page.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("user_name");
        String hashPassword = passwordEncoder.encode(name);
        System.out.println(passwordEncoder.matches(name, hashPassword));
        HttpSession session = request.getSession();
        session.setAttribute("username", name);
        String result = "DefaultUsername";
        Cookie cookie = new Cookie("username", name == null ? result : name);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(cookie);
        response.sendRedirect("/users");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
