package by.vladpr.movieratingsystem.database.impl;

import by.vladpr.movieratingsystem.database.AbstractConnectionPool;
import by.vladpr.movieratingsystem.exception.ConnectionPoolException;
import static by.vladpr.movieratingsystem.database.DatabaseParameters.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ConnectionPool implements AbstractConnectionPool {

    private static final AbstractConnectionPool connectionPool = new ConnectionPool();
    private String driver;
    private String connectionURL;
    private String user;
    private String password;
    private int poolSize;
    private static List<Connection> connectionList;
    private static List<Connection> usedConnections;

    public void init(String propertiesFileName) throws ConnectionPoolException {
        Properties dbProperties = new Properties();
        try (FileInputStream dbPropertiesFile = new FileInputStream(propertiesFileName)) {
            dbProperties.load(dbPropertiesFile);
            driver = dbProperties.getProperty(DATABASE_DRIVER);
            connectionURL = dbProperties.getProperty(DATABASE_URL);
            user = dbProperties.getProperty(DATABASE_USERNAME);
            password = dbProperties.getProperty(DATABASE_PASSWORD);
            poolSize = Integer.parseInt(dbProperties.getProperty(DATABASE_DEFAULT_POOL_SIZE));

            Class.forName(driver);
            usedConnections = new ArrayList<>();
            connectionList = new ArrayList<>(poolSize);
            for (int i = 0; i < 10; i++) {
                connectionList.add(DriverManager.getConnection(connectionURL, user, password));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new ConnectionPoolException("Error during initialization of connection pool", e);
        }
    }

    public static AbstractConnectionPool getInstance() {
        return connectionPool;
    }

    public void destroy() throws ConnectionPoolException {
        for (Connection connection: connectionList) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ConnectionPoolException("Error during closing of the connection pool.", e);
            }
        }
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
        return connectionURL;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
