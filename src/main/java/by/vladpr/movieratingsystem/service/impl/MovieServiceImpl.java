package by.vladpr.movieratingsystem.service.impl;

import by.vladpr.movieratingsystem.dao.DaoFactory;
import by.vladpr.movieratingsystem.dao.MovieDao;
import by.vladpr.movieratingsystem.entity.Movie;
import by.vladpr.movieratingsystem.exception.DaoException;
import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.service.MovieService;

import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService {

    @Override
    public void addMovie(Movie movie) throws ServiceException {
        MovieDao movieDao = DaoFactory.getInstance().getMovieDao();
        try {
            movieDao.save(movie);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Movie> getMovieList() throws ServiceException {
        MovieDao movieDao = DaoFactory.getInstance().getMovieDao();
        List<Movie> list;
        try {
            list = movieDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public Optional<Movie> getById(long id) throws ServiceException {
        MovieDao movieDao = DaoFactory.getInstance().getMovieDao();
        Optional<Movie> movie;
        try {
            movie = movieDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return Optional.empty();
    }

    @Override
    public void updateMovie(Movie movie) {
        MovieDao movieDao = DaoFactory.getInstance().getMovieDao();

    }
}
