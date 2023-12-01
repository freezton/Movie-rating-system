package by.vladpr.movieratingsystem.controller.command.impl;

import by.vladpr.movieratingsystem.controller.command.CommandName;
import by.vladpr.movieratingsystem.entity.User;
import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.controller.command.Command;
import by.vladpr.movieratingsystem.exception.UserAlreadyExistsException;
import by.vladpr.movieratingsystem.exception.ValidationException;
import by.vladpr.movieratingsystem.service.ServiceFactory;
import by.vladpr.movieratingsystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class RegisterCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);
    private static final String USERNAME_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";

    private static final int SUCCESSFUL_REGISTRATION = 1;
    private static final int INCORRECT_DATA = 2;
    private static final int USER_ALREADY_EXISTS = 3;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder viewPath = new StringBuilder();
        HttpSession session = request.getSession();
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = new User();
            user.setUsername(request.getParameter(USERNAME_PARAM));
            user.setPassword(request.getParameter(PASSWORD_PARAM));
            userService.register(user);
            session.setAttribute("reg_message", SUCCESSFUL_REGISTRATION);
            viewPath.append(request.getContextPath()).append(CommandName.GO_TO_LOGIN_PAGE_COMMAND);
        } catch (ValidationException e) {
            LOGGER.warn("Incorrect data used", e);
            session.setAttribute("reg_message", INCORRECT_DATA);
            viewPath.append(request.getHeader("referer"));
        } catch (UserAlreadyExistsException e) {
            LOGGER.info("Such user already exists", e);
            session.setAttribute("reg_message", USER_ALREADY_EXISTS);
            viewPath.append(request.getHeader("referer"));
        } catch (ServiceException e) {
            LOGGER.error("Error during registration", e);
            viewPath.append(request.getHeader("referer"));
            viewPath.append(request.getContextPath()).append(CommandName.ERROR503_COMMAND);
        }
        response.sendRedirect(viewPath.toString());
    }
}
