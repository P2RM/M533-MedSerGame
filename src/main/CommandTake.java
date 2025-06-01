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

        currentLocation.removeItem(obj);
        game.getPlayer().addItem(obj);

        // pour Pierre : on déverrouille automatiquement les lieux si la clé est la bonne
        for (Location[] row : game.getMap().getPrintableGrid()) {
            for (Location loc : row) {
                if (loc != null && loc.isLocked() && loc.canUnlock(obj.getName())) {
                    loc.unlock();
                }
            }
        }

        return "Vous avez ramassé : " + obj.getName();
    }
}