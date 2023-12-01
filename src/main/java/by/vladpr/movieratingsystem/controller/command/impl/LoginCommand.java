package by.vladpr.movieratingsystem.controller.command.impl;

import by.vladpr.movieratingsystem.controller.command.CommandName;
import by.vladpr.movieratingsystem.entity.Role;
import by.vladpr.movieratingsystem.entity.User;
import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.controller.command.Command;
import by.vladpr.movieratingsystem.exception.ValidationException;
import by.vladpr.movieratingsystem.service.ServiceFactory;
import by.vladpr.movieratingsystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private static final String USERNAME_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";

    private static final int USER_DOES_NOT_EXIT = 1;
    private static final int INVALID_CREDENTIALS = 2;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter(USERNAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        StringBuilder viewPath = new StringBuilder();
        HttpSession session = request.getSession();
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            Optional<User> user = userService.login(username, password);
            if (user.isPresent()) {
                session.setAttribute("userId", user.get().getId());
                session.setAttribute("username", user.get().getUsername());
                session.setAttribute("role", user.get().getRole().toString());

                response.sendRedirect(request.getContextPath() + CommandName.GET_MOVIES_COMMAND);
            } else {
                session.setAttribute("login_message", USER_DOES_NOT_EXIT);
                response.sendRedirect(request.getHeader("referer"));
            }
        } catch (ValidationException e) {
            LOGGER.warn("Invalid user credentials", e);
            session.setAttribute("login_message", INVALID_CREDENTIALS);
            response.sendRedirect(request.getHeader("referer"));
        } catch (ServiceException e) {
            LOGGER.error("Error during authorization", e);
            viewPath.append(request.getContextPath()).append(CommandName.ERROR503_COMMAND);
            response.sendRedirect(viewPath.toString());
        }
    }

    private String defineViewPath(Role role, HttpServletRequest request) {
        StringBuilder viewPath = new StringBuilder();
        switch (role) {
            case USER -> {
                return viewPath.append(request.getContextPath()).append(CommandName.GO_TO_MOVIES_PAGE_COMMAND).toString();
            }
            case ADMIN -> {
                return viewPath.append(request.getContextPath()).append(CommandName.GO_TO_ADMIN_PAGE_COMMAND).toString();
            }
            default -> {
                return null;
            }
        }
    }
}
