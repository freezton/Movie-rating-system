package by.vladpr.movieratingsystem.controller.command.impl;

import by.vladpr.movieratingsystem.controller.command.CommandName;
import by.vladpr.movieratingsystem.controller.command.SessionAttribute;
import by.vladpr.movieratingsystem.controller.command.ViewPath;
import by.vladpr.movieratingsystem.entity.Role;
import by.vladpr.movieratingsystem.entity.User;
import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.controller.command.Command;
import by.vladpr.movieratingsystem.exception.ValidationException;
import by.vladpr.movieratingsystem.service.ServiceFactory;
import by.vladpr.movieratingsystem.service.UserService;
import jakarta.servlet.ServletException;
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
    private static final int FAILED_LOGIN = 3;

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
                session.setAttribute(SessionAttribute.USER_ID, user.get().getId());
                session.setAttribute(SessionAttribute.USERNAME, user.get().getUsername());
                session.setAttribute(SessionAttribute.ROLE_ID, user.get().getRole().toString());
                response.sendRedirect(defineViewPath(user.get().getRole(), request));
            } else {
                session.setAttribute(SessionAttribute.LOGIN_MESSAGE, USER_DOES_NOT_EXIT);
                response.sendRedirect(request.getHeader("referer"));
            }
        } catch (ValidationException e) {
            LOGGER.warn("Invalid user credentials", e);
            session.setAttribute(SessionAttribute.LOGIN_MESSAGE, INVALID_CREDENTIALS);
            response.sendRedirect(request.getHeader("referer"));
        } catch (ServiceException e) {
            LOGGER.error("Error during authorization", e);
            session.setAttribute(SessionAttribute.LOGIN_MESSAGE, FAILED_LOGIN);
            viewPath.append(request.getContextPath()).append(CommandName.WRONG_REQUEST_COMMAND);
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
