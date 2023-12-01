package by.vladpr.movieratingsystem.controller.command;

import by.vladpr.movieratingsystem.controller.command.impl.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static by.vladpr.movieratingsystem.controller.command.CommandName.*;

public final class CommandProvider {

    private static final CommandProvider instance;
    private final Map<String, Command> repository;

    static {
        instance = new CommandProvider();
    }

    {
        repository = new HashMap<>();
    }

    private CommandProvider() {
        repository.put(LOGIN_COMMAND, new LoginCommand());
        repository.put(REGISTER_COMMAND, new RegisterCommand());
        repository.put(GO_TO_LOGIN_PAGE_COMMAND, new ForwardCommand(ViewPath.REDIRECT_LOGIN_FORM));
        repository.put(GO_TO_REGISTRATION_PAGE_COMMAND, new ForwardCommand(ViewPath.REDIRECT_REGISTRATION_FORM));
        repository.put(GO_TO_MOVIES_PAGE_COMMAND, new ForwardCommand(ViewPath.MOVIES_PAGE));
        repository.put(GO_TO_ADMIN_PAGE_COMMAND, new ForwardCommand(ViewPath.ADMIN_PAGE));
        repository.put(ADD_MOVIE_COMMAND, new AddMovieCommand());
        repository.put(CHANGE_LOCALE_COMMAND, new ChangeLocaleCommand());
        repository.put(LOGOUT_COMMAND, new LogoutCommand());
        repository.put(GET_USERS_COMMAND, new GetUsersCommand());
        repository.put(UPDATE_USER_COMMAND, new UpdateUserCommand());
        repository.put(UPDATE_MOVIE_COMMAND, new UpdateMovieCommand());
        repository.put(GET_MOVIES_COMMAND, new GetMoviesCommand());
        repository.put(GET_MOVIE_COMMAND, new GetMovieCommand());

        repository.put(ERROR503_COMMAND, new ForwardCommand(ViewPath.ERROR503_PAGE));
        repository.put(ERROR404_COMMAND, new ForwardCommand(ViewPath.ERROR404_PAGE));
    }

    public Command getCommand(HttpServletRequest request) {
        String commandName = request.getServletPath();

        Command command = repository.get(commandName);
        if (command == null) {
            return repository.get(ERROR404_COMMAND);
        }
        return command;
    }

    public static CommandProvider getInstance() {
        return instance;
    }
}
