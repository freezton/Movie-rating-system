package by.vladpr.movieratingsystem.dao.impl;

import by.vladpr.movieratingsystem.dao.MovieDao;
import by.vladpr.movieratingsystem.database.AbstractQueryCreator;
import by.vladpr.movieratingsystem.entity.Movie;
import by.vladpr.movieratingsystem.exception.DaoException;

import java.util.Optional;

public class MovieDaoImpl extends AbstractQueryCreator implements MovieDao {
    @Override
    public Optional<Movie> findById(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void save(Movie item) throws DaoException {

    }

    @Override
    public void removeById(long id) throws DaoException {

    }
}
