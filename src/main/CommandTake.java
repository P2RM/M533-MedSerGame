package main;

public class CommandTake implements ICommand {

    private String verb;
    private String description;
    private String objectName;

    public CommandTake(String verb, String description) {
        this.verb = verb;
        this.description = description;
        this.objectName = null;
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
        if (objectName == null || objectName.isEmpty()) {
            return "Quel objet voulez-vous ramasser ?";
        }

        Location currentLocation = game.getMap().getPlayerLocation();
        Object obj = currentLocation.getItemByName(objectName);

        if (obj == null) {
            return "Cet objet n'est pas présent ici.";
        }

        currentLocation.removeItem(obj);//enleve l'objet
        game.getPlayer().addItem(obj);//ajoute à l'inventaire

       

        return "Vous avez ramassé : " + obj.getName();
    }
}