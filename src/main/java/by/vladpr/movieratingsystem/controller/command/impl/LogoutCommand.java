package by.vladpr.movieratingsystem.controller.command.impl;

import by.vladpr.movieratingsystem.controller.command.Command;
import by.vladpr.movieratingsystem.controller.command.CommandName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class LogoutCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(DeleteReviewCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath().concat(CommandName.GO_TO_LOGIN_PAGE_COMMAND));
    }
}
