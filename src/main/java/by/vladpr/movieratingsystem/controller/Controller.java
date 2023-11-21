package by.vladpr.movieratingsystem.controller;

import java.io.*;

import by.vladpr.movieratingsystem.service.Command;
import by.vladpr.movieratingsystem.service.CommandProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "controller", value = "/controller")
public class Controller extends HttpServlet {

    private static final String JSP_PATH = "/WEB-INF/view/%s";
    private static final String COMMAND_PARAMETER = "command";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String page = request.getParameter("page");
        if (page != null) {
            request.getRequestDispatcher(String.format(JSP_PATH, page)).forward(request, response);
        } else {
            executeCommand(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeCommand(request, response);
    }

    private void executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND_PARAMETER);
        Command command = CommandProvider.getInstance().getCommand(commandName);
        String page;
        try {
            page = command.execute(request);
        } catch (Exception e) {
            page = ""; // ERROR PAGE!!!
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        if (requestDispatcher != null) {
            requestDispatcher.forward(request, response);
        } else {
            // SHOW ERROR MESSAGE!!!
        }
    }

}