package by.vladpr.movieratingsystem.dao.impl;

import by.vladpr.movieratingsystem.dao.UserDao;
import by.vladpr.movieratingsystem.database.AbstractQueryCreator;
import by.vladpr.movieratingsystem.entity.User;
import by.vladpr.movieratingsystem.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractQueryCreator implements UserDao {
    private static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id=?";
    private static final String SAVE_USER_QUERY = "INSERT INTO users (username, password, role_id, status) VALUES (?, ?, ?, ?)";
    private static final String REMOVE_USER_BY_ID_QUERY = "DELETE FROM users WHERE id=?";
    private static final String FIND_USER_BY_USERNAME_QUERY = "SELECT * FROM users WHERE username=?";
    private static final String FIND_USER_BY_ROLE_ID_QUERY = "SELECT * FROM users WHERE role_id=?";

    @Override
    public Optional<User> findById(long id) throws DaoException {
        User user;
        try (PreparedStatement preparedStatement = createStatement(FIND_USER_BY_ID_QUERY, id)) {
            user = findUser(preparedStatement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void save(User user) throws DaoException {
        try (PreparedStatement preparedStatement = createStatement(
                SAVE_USER_QUERY,
                user.getUsername(),
                user.getPassword(),
                user.getRoleId(),
                user.getStatus()
        )) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeById(long id) throws DaoException {
        try (PreparedStatement preparedStatement = createStatement(REMOVE_USER_BY_ID_QUERY, id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) throws DaoException {
        User user;
        try (PreparedStatement preparedStatement = createStatement(FIND_USER_BY_USERNAME_QUERY, username)) {
            user = findUser(preparedStatement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findByRoleId(long roleId) throws DaoException {
        List<User> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = createStatement(FIND_USER_BY_ROLE_ID_QUERY, roleId)) {
            list = findUserList(preparedStatement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return list;
    }

    private User findUser(PreparedStatement preparedStatement) throws DaoException {
        User user = null;
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRoleId(resultSet.getInt("role_id"));
                user.setStatus(resultSet.getInt("status"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    private List<User> findUserList(PreparedStatement preparedStatement) throws DaoException {
        List<User> list = new ArrayList<>();
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRoleId(resultSet.getInt("role_id"));
                user.setStatus(resultSet.getInt("status"));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return list;
    }
}
