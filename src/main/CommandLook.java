package main;
 
public class CommandLook implements ICommand {
 
    private String verb;
    private String description;
 
    public CommandLook(String verb, String description) {
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
        Location currentLocation = game.getMap().getPlayerLocation();
        if (currentLocation != null) 
            return "You are in: " + currentLocation.getNom() + "\n" +
                   currentLocation.getDescr();
        else
            return "You are not in a valid location.";
    }
}
 