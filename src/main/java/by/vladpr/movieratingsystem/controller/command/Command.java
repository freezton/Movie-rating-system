package by.vladpr.movieratingsystem.controller.command;

import by.vladpr.movieratingsystem.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public interface Command {

    static final String VIEW_PATH = "/WEB-INF/jsp/%s";

    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
