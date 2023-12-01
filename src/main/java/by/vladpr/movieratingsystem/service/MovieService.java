package by.vladpr.movieratingsystem.service;

import by.vladpr.movieratingsystem.entity.Movie;
import by.vladpr.movieratingsystem.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    void addMovie(Movie movie) throws ServiceException;

    List<Movie> getMovieList() throws ServiceException;

    Optional<Movie> getById(long id) throws ServiceException;

    void updateMovie(Movie movie);

}
