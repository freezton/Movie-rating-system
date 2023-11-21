package by.vladpr.movieratingsystem.service.impl;

import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.service.Command;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;

public class LoginCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        return null;
    }
}
