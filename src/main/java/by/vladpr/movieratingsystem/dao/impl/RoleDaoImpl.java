package by.vladpr.movieratingsystem.dao.impl;

import by.vladpr.movieratingsystem.dao.RoleDao;
import by.vladpr.movieratingsystem.database.AbstractQueryCreator;
import by.vladpr.movieratingsystem.entity.Role;
import by.vladpr.movieratingsystem.entity.User;
import by.vladpr.movieratingsystem.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RoleDaoImpl extends AbstractQueryCreator implements RoleDao {

    private static final String FIND_ROLE_BY_ID_QUERY = "SELECT * FROM roles WHERE id=?";
    private static final String FIND_ROLE_BY_NAME_QUERY = "SELECT * FROM roles WHERE role_name=?";
    private static final String SAVE_ROLE_QUERY = "INSERT INTO roles (role_name) VALUES (?)";
    private static final String REMOVE_ROLE_BY_ID_QUERY = "DELETE FROM roles WHERE id=?";

    @Override
    public Optional<Role> findById(long id) throws DaoException {
        Role role;
        try (PreparedStatement preparedStatement = createStatement(FIND_ROLE_BY_ID_QUERY, id)) {
            role = findRole(preparedStatement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(role);
    }

    @Override
    public void save(Role role) throws DaoException {
        try (PreparedStatement preparedStatement = createStatement(
                SAVE_ROLE_QUERY,
                role.getRoleName()
        )) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeById(long id) throws DaoException {
        try (PreparedStatement preparedStatement = createStatement(REMOVE_ROLE_BY_ID_QUERY, id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Role> findRoleByName(String name) throws DaoException {
        Role role;
        try (PreparedStatement preparedStatement = createStatement(FIND_ROLE_BY_NAME_QUERY, name)) {
            role = findRole(preparedStatement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(role);
    }

    private Role findRole(PreparedStatement preparedStatement) throws DaoException {
        Role role = null;
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setRoleName(resultSet.getString("role_name"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return role;
    }
}
