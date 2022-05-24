package ru.avenue.dev.servlets.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.avenue.dev.servlets.repositories.realisations.UsersRepositoryJdbcImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        HikariConfig config = new HikariConfig();
        Properties properties = new Properties();
        try {
            properties.load(context.getResourceAsStream("/WEB-INF/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setDriverClassName(properties.getProperty("db.hikari.driver-name"));
        config.setUsername(properties.getProperty("db.username"));
        config.setPassword(properties.getProperty("db.password"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.max-pool-size")));
        HikariDataSource source = new HikariDataSource(config);
        context.setAttribute("dataSource", source);

        context.setAttribute("usersRepository", new UsersRepositoryJdbcImpl(source));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
