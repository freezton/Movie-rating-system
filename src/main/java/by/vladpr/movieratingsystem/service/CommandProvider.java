package by.vladpr.movieratingsystem.service;

import by.vladpr.movieratingsystem.service.impl.LoginCommand;
import org.apache.log4j.Logger;


import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private static final Logger log = Logger.getLogger(CommandProvider.class);
    private static final CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> repository = new HashMap<>();

    private CommandProvider() {
        repository.put(CommandName.DO_LOGIN, new LoginCommand());
    }

    public Command getCommand(String commandName) {
        Command command;
        try {
            command = repository.get(CommandName.valueOf(commandName.toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            log.error(e.getMessage());
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }

    public static CommandProvider getInstance() {
        return instance;
    }
}
