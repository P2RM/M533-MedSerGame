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
        Object object = game.getPlayer().getObject(objectName);

        if (object == null) {
            return "You don't have this object in your inventory.";
        }

        // Nouveau comportement : déverrouiller la zone si l'objet est une clé
        if (object.getName().equalsIgnoreCase("clé") && game.getMap().getPlayerLocation().isLocked()) {
            game.getMap().getPlayerLocation().setLocked(false);
            return "You inspect the " + object.getName() + ".\nIt's a key... It unlocks the area!";
        }

        return "Inspecting " + object.getName() + ":\n" + object.getDescription();
    }
}