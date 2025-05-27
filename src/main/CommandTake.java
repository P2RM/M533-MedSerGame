package main;
 
public class CommandTake implements ICommand {
 
    private String verb;
    private String description;
    private String objectName;
 
    public CommandTake(String verb, String description) {
        this.verb = verb;
        this.description = description;
    }
 
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
 
    @Override
    public String getVerb() {
        return verb;
    }
 
    @Override
    public String getDescription() {
        return description;
    }
 
    @Override
    public String execute(Game game) {
        Location current = game.getMap().getPlayerLocation();
        Object object = current.removeObjectByName(objectName);
 
        if (object == null) {
            return "There is no object with that name here.";
        } else {
            game.getPlayer().addToInventory(object);
            return "You picked up: " + object.getName();
        }
    }

    public void setTarget(String param) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTarget'");
    }
}