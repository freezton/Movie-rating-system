package by.vladpr.movieratingsystem.database;

import java.sql.Connection;

public interface AbstractConnectionPool {

    Connection getConnection();
    boolean releaseConnection(Connection connection);
    String getUrl();
    String getUser();
    String getPassword();
}
