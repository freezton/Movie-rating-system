package by.vladpr.movieratingsystem.controller.command.impl;

import by.vladpr.movieratingsystem.controller.command.CommandName;
import by.vladpr.movieratingsystem.dao.DaoFactory;
import by.vladpr.movieratingsystem.entity.User;
import by.vladpr.movieratingsystem.exception.DaoException;
import by.vladpr.movieratingsystem.exception.ServiceException;
import by.vladpr.movieratingsystem.controller.command.Command;
import by.vladpr.movieratingsystem.exception.ValidationException;
import by.vladpr.movieratingsystem.service.ServiceFactory;
import by.vladpr.movieratingsystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class RegisterCommand implements Command {

    private final String USERNAME_PARAM = "username";
    private final String PASSWORD_PARAM = "password";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = new User();
            user.setUsername(request.getParameter(USERNAME_PARAM));
            user.setPassword(request.getParameter(PASSWORD_PARAM));
            userService.register(user);

//            HttpSession session = request.getSession();
//            session.setAttribute();
            response.sendRedirect(request.getContextPath() + CommandName.GO_TO_LOGIN_PAGE_COMMAND);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
