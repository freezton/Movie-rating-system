package by.vladpr.movieratingsystem.controller.command;

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

        repository.put(WRONG_REQUEST_COMMAND, new WrongRequestCommand());
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
