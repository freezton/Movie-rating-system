package by.vladpr.movieratingsystem.controller;

import by.vladpr.movieratingsystem.database.impl.ConnectionPool;
import by.vladpr.movieratingsystem.exception.ConnectionPoolException;
import by.vladpr.movieratingsystem.temp.Test;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URISyntaxException;
import java.util.Objects;

public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);
    private static final String DATABASE_PROPERTIES = "database.properties";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().init(Objects.requireNonNull(Test.class.getClassLoader().getResource(DATABASE_PROPERTIES)).toURI().getPath());
        } catch (ConnectionPoolException | URISyntaxException e) {
            throw new RuntimeException("Exception during initialization of connection pool!", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().destroy();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Exception during destroying of connection pool", e);
        }
    }
}
