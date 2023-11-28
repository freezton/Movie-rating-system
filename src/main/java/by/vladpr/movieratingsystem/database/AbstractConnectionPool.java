package by.vladpr.movieratingsystem.database;

import by.vladpr.movieratingsystem.exception.ConnectionPoolException;

import java.sql.Connection;

public interface AbstractConnectionPool {

    void init(String propertiesFileName) throws ConnectionPoolException;
    void destroy() throws ConnectionPoolException;
    Connection getConnection();
    boolean releaseConnection(Connection connection);
    String getUrl();
    String getUser();
    String getPassword();
}
