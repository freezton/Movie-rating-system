package by.vladpr.movieratingsystem.controller.command.impl;

import by.vladpr.movieratingsystem.controller.command.CommandName;
import by.vladpr.movieratingsystem.controller.utils.SessionAttributes;
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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher(String.format(VIEW_PATH, PAGE)).forward(request, response);
        String username = request.getParameter(USERNAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);

        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            Optional<User> user = userService.login(username, password);
            if (user.isPresent()) {
                HttpSession session = request.getSession();
                session.setAttribute(SessionAttributes.USER_ID, user.get().getId());
                session.setAttribute(SessionAttributes.USERNAME, user.get().getUsername());
                session.setAttribute(SessionAttributes.ROLE_ID, user.get().getRole().toString());

//                String viewPath = defineViewPath();
                String viewPath = request.getContextPath() + CommandName.GO_TO_MOVIES_PAGE_COMMAND;
                response.sendRedirect(viewPath);
            } else {
                response.sendRedirect(request.getHeader("referer"));
            }
        } catch (ValidationException e) {
            LOGGER.warn("Invalid user credentials", e);
        } catch (ServiceException e) {
            LOGGER.error("Service exception!!!");
            String viewPath = request.getContextPath() + CommandName.WRONG_REQUEST_COMMAND;
            response.sendRedirect(viewPath);
        }
    }

    private String defineViewPath() {
        return "geg";
    }
}
