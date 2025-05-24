package main;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

    private Map<String, ICommand> commands = new HashMap<>();

    public void addCommand(String name, ICommand command) {
        commands.put(name, command);
    }

    public Map<String, ICommand> getCommands() {
        return commands;
    }
}
