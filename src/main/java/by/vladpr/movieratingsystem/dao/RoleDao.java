package by.vladpr.movieratingsystem.dao;

import by.vladpr.movieratingsystem.entity.Role;
import by.vladpr.movieratingsystem.exception.DaoException;

import java.util.Optional;

public interface RoleDao extends Dao<Role> {

    Optional<Role> findRoleByName(String name) throws DaoException;
}
