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
        this.objectName = objectName.toLowerCase();
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

        if (objectName == null || objectName.isEmpty()) {
            return "You must specify an object to take.";
        }

        Object object = current.removeObjectByName(objectName);

        if (object == null) {
            return "There is no object with that name here.";
        } else {
            game.getPlayer().addToInventory(object);
            return "You picked up: " + object.getName();
        }
    }
}