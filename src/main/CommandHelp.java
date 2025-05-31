package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandHelp implements ICommand {

    private String verb;
    private String description;

    public CommandHelp(String verb, String description) {
        this.verb = verb;
        this.description = description;
    }

    @Override
    public String getVerb() {
        return this.verb;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String execute(Game game) {
        StringBuilder result = new StringBuilder("Available commands:\n");

        List<String> verbs = new ArrayList<>(game.getCommandRegistry().getCommands().keySet());
        Collections.sort(verbs);

        for (String verb : verbs) {
            ICommand command = game.getCommandRegistry().getCommands().get(verb);
            if (command != null) {
                result.append("- ").append(verb).append(" : ").append(command.getDescription()).append("\n");
            }
        }

        return result.toString();
    }
}