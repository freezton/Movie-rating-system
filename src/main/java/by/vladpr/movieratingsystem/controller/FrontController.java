package by.vladpr.movieratingsystem.controller;

import java.io.*;

import by.vladpr.movieratingsystem.controller.command.Command;
import by.vladpr.movieratingsystem.controller.command.CommandProvider;
import by.vladpr.movieratingsystem.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "controller", value = "/")
public class FrontController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(FrontController.class);
    private static final String JSP_PATH = "/WEB-INF/view/%s";
    private static final String COMMAND_PARAMETER = "command";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        LOGGER.info(request.getServletPath());
//        LOGGER.info(request.getPathInfo());
        LOGGER.info(request.getContextPath());
        Command command = CommandProvider.getInstance().getCommand(request);
        command.execute(request, response);
    }

}