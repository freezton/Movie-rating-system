package by.vladpr.movieratingsystem.service;

import by.vladpr.movieratingsystem.entity.User;
import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.exception.UserAlreadyExistsException;
import by.vladpr.movieratingsystem.exception.ValidationException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void register(User user) throws ValidationException, ServiceException, UserAlreadyExistsException;

    Optional<User> login(String username, String password) throws ValidationException, ServiceException;

    Optional<User> findById(long id) throws ServiceException;

    void updateUser(User user) throws ServiceException;

    List<User> getUserList() throws ServiceException;
}
