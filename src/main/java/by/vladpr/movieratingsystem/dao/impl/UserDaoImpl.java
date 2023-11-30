package by.vladpr.movieratingsystem.dao.impl;

import by.vladpr.movieratingsystem.dao.UserDao;
import by.vladpr.movieratingsystem.database.QueryProvider;
import by.vladpr.movieratingsystem.entity.Role;
import by.vladpr.movieratingsystem.entity.User;
import by.vladpr.movieratingsystem.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends QueryProvider implements UserDao {
    private static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id=?";
    private static final String SAVE_USER_QUERY = "INSERT INTO users (username, password, role, status, is_banned) VALUES (?, ?, ?, ?, ?)";
    private static final String REMOVE_USER_BY_ID_QUERY = "DELETE FROM users WHERE id=?";
    private static final String FIND_USER_BY_USERNAME_QUERY = "SELECT * FROM users WHERE username=?";
    private static final String FIND_USER_BY_USERNAME_AND_PASSWORD_QUERY = "SELECT * FROM users WHERE username=? AND password=?";
    private static final String FIND_USER_BY_ROLE_QUERY = "SELECT * FROM users WHERE role=?";
    private static final String UPDATE_USER_INFO = "UPDATE users SET username=?, password=?, role=?, status=?, is_banned=? WHERE id=?";


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
                user.getRole().toString(),
                user.getStatus(),
                user.isBanned()
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
    public Optional<User> findByUsernameAndPassword(String username, String passwordHash) throws DaoException {
        User user;
        try (PreparedStatement preparedStatement = createStatement(FIND_USER_BY_USERNAME_AND_PASSWORD_QUERY, username, passwordHash)) {
            user = findUser(preparedStatement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findByRoleId(Role role) throws DaoException {
        List<User> list;
        try (PreparedStatement preparedStatement = createStatement(FIND_USER_BY_ROLE_QUERY, role.toString())) {
            list = findUserList(preparedStatement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    public boolean isUserExists(String username) throws DaoException {
        return findByUsername(username).isPresent();
    }

    @Override
    public void updateUserInfo(User user) throws DaoException {
        try (PreparedStatement preparedStatement = createStatement(
                SAVE_USER_QUERY,
                user.getUsername(),
                user.getPassword(),
                user.getRole().toString(),
                user.getStatus(),
                user.isBanned(),
                user.getId()
        )) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private User findUser(PreparedStatement preparedStatement) throws DaoException {
        User user = null;
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                user = createUser(resultSet);
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
                list.add(createUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return list;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        user.setStatus(resultSet.getInt("status"));
        user.setBanned(resultSet.getBoolean("is_banned"));
        return user;
    }
}
