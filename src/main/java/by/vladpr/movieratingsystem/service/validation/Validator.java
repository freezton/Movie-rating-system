package by.vladpr.movieratingsystem.service.validation;

import by.vladpr.movieratingsystem.entity.User;

public abstract class Validator {

    public static boolean isRegistrationDataValid(User user) {

        return true;
    }

    public static boolean isLoginDataValid(String username, String password) {

        return true;
    }
}
