package by.vladpr.movieratingsystem.dao;

import by.vladpr.movieratingsystem.dao.impl.MovieDaoImpl;
import by.vladpr.movieratingsystem.dao.impl.ReviewDaoImpl;
import by.vladpr.movieratingsystem.dao.impl.RoleDaoImpl;
import by.vladpr.movieratingsystem.dao.impl.UserDaoImpl;

public final class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final UserDao userDao = new UserDaoImpl();
    private final RoleDao roleDao = new RoleDaoImpl();
    private final MovieDao movieDao = new MovieDaoImpl();
    private final ReviewDao reviewDao = new ReviewDaoImpl();

    private DAOFactory() {}

    public UserDao getUserDao() {
        return userDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public MovieDao getMovieDao() {
        return movieDao;
    }

    public ReviewDao getReviewDao() {
        return reviewDao;
    }

    public static DAOFactory getInstance() {
        return instance;
    }


}
