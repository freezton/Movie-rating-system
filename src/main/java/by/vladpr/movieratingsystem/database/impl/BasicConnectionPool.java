package by.vladpr.movieratingsystem.database.impl;

import by.vladpr.movieratingsystem.database.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool {

    private static final ConnectionPool connectionPool = new BasicConnectionPool();
    private static final String URL = "jdbc:mysql://localhost:3306/movie_rating_system";
    private static final String USER = "mysql_user";
    private static final String PASSWORD = "12345Abc";
    private static List<Connection> connectionList;
    private static List<Connection> usedConnections;
    private static final int POOL_SIZE = 10;

    public static ConnectionPool getInstance() throws SQLException {
        if (connectionList == null) {
            usedConnections = new ArrayList<>();
            connectionList = new ArrayList<>(POOL_SIZE);
            for (int i = 0; i < 10; i++) {
                connectionList.add(DriverManager.getConnection(URL, USER, PASSWORD));
            }
        }
        return connectionPool;
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionList.remove(connectionList.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionList.add(connection);
        return usedConnections.remove(connection);
    }

    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public String getUser() {
        return USER;
    }

    @Override
    public String getPassword() {
        return PASSWORD;
    }
}
