package main;
import java.util.*;

public class CommandRegistry {
    private Map<String, ICommand> commands;

    public CommandRegistry() {
        this.commands = new HashMap<>();
    }

    public void addCommand(String key, ICommand command) {
        commands.put(key, command);
    }

    public ICommand getCommand(String key) {
        return commands.get(key);
    }

    public Set<String> getAllCommands() {
        return commands.keySet();
    }
}