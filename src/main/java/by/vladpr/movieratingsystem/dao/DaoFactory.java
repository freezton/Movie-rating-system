package by.vladpr.movieratingsystem.dao;

import by.vladpr.movieratingsystem.dao.impl.MovieDaoImpl;
import by.vladpr.movieratingsystem.dao.impl.ReviewDaoImpl;
import by.vladpr.movieratingsystem.dao.impl.UserDaoImpl;

public final class DaoFactory {

    private static final DaoFactory instance = new DaoFactory();

    private final UserDao userDao = new UserDaoImpl();
    private final MovieDao movieDao = new MovieDaoImpl();
    private final ReviewDao reviewDao = new ReviewDaoImpl();

    private DaoFactory() {}

    public UserDao getUserDao() {
        return userDao;
    }

    public MovieDao getMovieDao() {
        return movieDao;
    }

    public ReviewDao getReviewDao() {
        return reviewDao;
    }

    public static DaoFactory getInstance() {
        return instance;
    }


}
