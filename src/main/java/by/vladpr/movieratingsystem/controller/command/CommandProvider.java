package by.vladpr.movieratingsystem.controller.command;

import by.vladpr.movieratingsystem.controller.command.impl.ForwardCommand;
import by.vladpr.movieratingsystem.controller.command.impl.LoginCommand;
import by.vladpr.movieratingsystem.controller.command.impl.RegisterCommand;
import by.vladpr.movieratingsystem.controller.command.impl.WrongRequestCommand;
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

        repository.put(WRONG_REQUEST_COMMAND, new ForwardCommand(ViewPath.WRONG_REQUEST));
    }

    public Command getCommand(HttpServletRequest request) {
        String commandName = request.getServletPath();

        Command command = repository.get(commandName);
        if (command == null) {
            return repository.get(WRONG_REQUEST_COMMAND);
        }
        return command;
    }

    public static CommandProvider getInstance() {
        return instance;
    }
}
