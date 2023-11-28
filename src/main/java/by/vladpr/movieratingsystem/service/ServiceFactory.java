package by.vladpr.movieratingsystem.service;

import by.vladpr.movieratingsystem.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService;

    private ServiceFactory() {
        userService = new UserServiceImpl();
    }

    public UserService getUserService() {
        return userService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
