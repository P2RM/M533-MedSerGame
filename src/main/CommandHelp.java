package main;
 
import java.util.Map;
 
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
    String result = "Available commands:\n";
 
    List<String> verbs = new ArrayList<>(game.getCommandRegistry().getCommands().keySet());
    Collections.sort(verbs);
 
    for (String verb : verbs) {
        ICommand command = game.getCommandRegistry().getCommands().get(verb);
        result += "- " + verb + " : " + command.getDescription();
    }
 
    return result;
    }
}