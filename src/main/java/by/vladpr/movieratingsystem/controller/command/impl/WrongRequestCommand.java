package by.vladpr.movieratingsystem.controller.command.impl;

import by.vladpr.movieratingsystem.controller.command.Command;
import by.vladpr.movieratingsystem.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class WrongRequestCommand implements Command {

    private static final String PAGE = "wrong-request.jsp";
//    private static final String PAGE = "login.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(String.format(VIEW_PATH, PAGE)).forward(request, response);
        return null;
    }
}
