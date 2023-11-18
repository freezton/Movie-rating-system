package by.vladpr.movieratingsystem.database;

import by.vladpr.movieratingsystem.database.impl.ConnectionPool;
import by.vladpr.movieratingsystem.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractQueryCreator {

    protected PreparedStatement createStatement(String query, Object... params) throws DaoException {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1, params[i]);
            }
            ConnectionPool.getInstance().releaseConnection(connection);
            return preparedStatement;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
