package by.vladpr.movieratingsystem.controller.command.impl;

import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
