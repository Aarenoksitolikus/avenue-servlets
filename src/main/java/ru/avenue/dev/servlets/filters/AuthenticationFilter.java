package ru.avenue.dev.servlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //получаем сессию пользователя
        //ищем пользователя с такой сессией в нашей базе данных
        //если он есть - смотрим его уровень доступа
        //если он достаточно высокий - пускаем его на запрашиваемую страницу
        //если нет - не пускаем
        //если пользователя вообще нет в базе - тоже не пускаем, а предлагаем зарегистрироваться (например)

        //по кукам настраиваем персонализацию пользователя - то есть то, как ЛИЧНО для него будет выглядеть страницы
        //НИКОГДА не храним пароли в открытом виде - только в качестве хешей
        Cookie[] cookies = request.getCookies();
        for (Cookie current : cookies) {
            if (current.getName().equals("JSESSIONID")) {
                if (!current.getValue().equals("14B819914AFB03162A4498B5BA6626EC")) {
                    response.sendRedirect("/users");
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
