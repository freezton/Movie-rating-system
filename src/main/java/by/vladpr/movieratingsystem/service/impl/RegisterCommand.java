package by.vladpr.movieratingsystem.service.impl;

import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.service.Command;
import jakarta.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return null;
    }
}
