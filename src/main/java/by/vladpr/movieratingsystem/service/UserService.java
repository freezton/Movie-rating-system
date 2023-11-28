package by.vladpr.movieratingsystem.service;

import by.vladpr.movieratingsystem.entity.User;
import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.exception.ValidationException;

import java.util.Optional;

public interface UserService {

    void register(User user) throws ValidationException, ServiceException;

    Optional<User> login(String username, String password) throws ValidationException, ServiceException;

}
