package main;
 
public class CommandInspect implements ICommand {
 
    private String verb;
    private String description;
    private String objectName;
 
    public CommandInspect(String verb, String description) {
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
    Object object = null;
    for (Object obj : game.getPlayer().getInventory()) {
        if (obj.getName().equalsIgnoreCase(objectName)) {
            object = obj;
            break;
        }
    }

    if (object == null) {
        return "You don't have this object in your inventory.";
    } else {
        return "Inspecting " + object.getName() + ":\n" + object.getDescription();
    }
}

}