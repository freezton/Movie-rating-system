package by.vladpr.movieratingsystem.service;

import by.vladpr.movieratingsystem.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;

public interface Command {

    String execute(HttpServletRequest request) throws ServiceException;
}
