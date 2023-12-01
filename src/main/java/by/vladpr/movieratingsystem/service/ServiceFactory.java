package by.vladpr.movieratingsystem.service;

import by.vladpr.movieratingsystem.service.impl.MovieServiceImpl;
import by.vladpr.movieratingsystem.service.impl.ReviewServiceImpl;
import by.vladpr.movieratingsystem.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService;
    private final MovieService movieService;
    private final ReviewService reviewService;

    private ServiceFactory() {
        userService = new UserServiceImpl();
        movieService = new MovieServiceImpl();
        reviewService = new ReviewServiceImpl();
    }

    public UserService getUserService() {
        return userService;
    }

    public MovieService getMovieService() {
        return movieService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
