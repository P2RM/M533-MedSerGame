package main;
import java.util.*;

public class CommandHelp implements ICommand {

    private String verb;
    private String description;

    public CommandHelp(String verb, String description) {
        this.verb = verb;
        this.description = description;
    }

    @Override
    public String getVerb() { return verb; }
    @Override
    public String getDescription() { return description; }

    @Override
    public String execute(Game game) {
        StringBuilder result = new StringBuilder("Commandes disponibles :\n");
        Set<String> verbs = game.getCommandRegistry().getAllCommands();
        for (String v : verbs) {
            ICommand cmd = game.getCommandRegistry().getCommand(v);
            result.append("- ").append(v).append(" : ").append(cmd.getDescription()).append("\n");
        }
        return result.toString();
    }
}