package by.vladpr.movieratingsystem.dao;

import by.vladpr.movieratingsystem.entity.Movie;
import by.vladpr.movieratingsystem.exception.DaoException;

import java.util.List;

public interface MovieDao extends Dao<Movie> {

    void updateMovieInfo(Movie movie) throws DaoException;
}
