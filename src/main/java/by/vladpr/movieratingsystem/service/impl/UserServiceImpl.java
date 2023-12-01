package by.vladpr.movieratingsystem.service.impl;

import by.vladpr.movieratingsystem.dao.DaoFactory;
import by.vladpr.movieratingsystem.dao.UserDao;
import by.vladpr.movieratingsystem.entity.Role;
import by.vladpr.movieratingsystem.entity.User;
import by.vladpr.movieratingsystem.exception.DaoException;
import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.exception.UserAlreadyExistsException;
import by.vladpr.movieratingsystem.exception.ValidationException;
import by.vladpr.movieratingsystem.service.UserService;
import by.vladpr.movieratingsystem.service.validation.Validator;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Override
    public void register(User user) throws ValidationException, ServiceException, UserAlreadyExistsException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        if (!Validator.isRegistrationDataValid(user)) {
            throw new ValidationException("Invalid user data");
        }
        try {
            if (userDao.isUserExists(user.getUsername())) {
                throw new UserAlreadyExistsException("Such user already exists");
            }
            // add data check

            String passwordHash = DigestUtils.sha256Hex(user.getPassword());
            user.setPassword(passwordHash);
            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> login(String username, String password) throws ValidationException, ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        if (!Validator.isLoginDataValid(username, password)) {
            throw new ValidationException("Invalid user credentials");
        }
        try {
            String passwordHash = DigestUtils.sha256Hex(password);
            return userDao.findByUsernameAndPassword(username, passwordHash);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findById(long id) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        Optional<User> user;
        try {
            user = userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            userDao.updateUserInfo(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getUserList() throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        List<User> list;
        try {
            list = userDao.findByRoleId(Role.USER);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return list;
    }
}
