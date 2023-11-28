package by.vladpr.movieratingsystem.dao;

import by.vladpr.movieratingsystem.entity.Role;
import by.vladpr.movieratingsystem.entity.User;
import by.vladpr.movieratingsystem.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> findByUsername(String username) throws DaoException;

    Optional<User> findByUsernameAndPassword(String username, String passwordHash) throws DaoException;

    List<User> findByRoleId(Role role) throws DaoException;

}
