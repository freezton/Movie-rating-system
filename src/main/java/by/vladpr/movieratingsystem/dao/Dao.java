package by.vladpr.movieratingsystem.dao;

import by.vladpr.movieratingsystem.exception.DaoException;

import javax.swing.tree.RowMapper;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(long id) throws DaoException;

    void save(T item) throws DaoException;

    void removeById(long id) throws DaoException;
}
