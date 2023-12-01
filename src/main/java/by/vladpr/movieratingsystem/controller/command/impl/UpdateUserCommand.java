package by.vladpr.movieratingsystem.controller.command.impl;

import by.vladpr.movieratingsystem.controller.command.Command;
import by.vladpr.movieratingsystem.controller.command.CommandName;
import by.vladpr.movieratingsystem.controller.command.ViewPath;
import by.vladpr.movieratingsystem.entity.User;
import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.service.ServiceFactory;
import by.vladpr.movieratingsystem.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

public class UpdateUserCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(UpdateUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        long id = Long.parseLong(request.getParameter("userId"));
        try {
            Optional<User> user = userService.findById(id);
            if (user.isPresent()) {
                user.get().setBanned(Boolean.parseBoolean(request.getParameter("ban")));
                user.get().setStatus(Math.max(0, Math.min(100, Integer.parseInt(request.getParameter("status")))));
                userService.updateUser(user.get());
                response.sendRedirect(request.getContextPath()+ CommandName.GET_USERS_COMMAND);
            }
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            LOGGER.error("Error while updating user info", e);
            response.sendRedirect(request.getContextPath().concat(ViewPath.ERROR503_PAGE));
        }
    }
}
