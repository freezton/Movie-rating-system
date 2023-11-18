package by.vladpr.movieratingsystem.dao;

import by.vladpr.movieratingsystem.dao.impl.RoleDaoImpl;
import by.vladpr.movieratingsystem.dao.impl.UserDaoImpl;

public final class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final UserDao userDao = new UserDaoImpl();
    private final RoleDao roleDao = new RoleDaoImpl();

    private DAOFactory() {}

    public UserDao getUserDao() {
        return userDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public static DAOFactory getInstance() {
        return instance;
    }


}
