package main;

public class CommandInspect implements ICommand {

    private String verb;
    private String description;
    private String objectName;

    public CommandInspect(String verb, String description) {
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
            // pour Romain de Raul : ça sa affiche l’inventaire
            if (game.getPlayer().getInventory().isEmpty()) {
                return "Votre inventaire est vide.";
            }
            StringBuilder sb = new StringBuilder("Inventaire :\n");
            for (Object obj : game.getPlayer().getInventory()) {
                sb.append("- ").append(obj.getName()).append("\n");
            }
            return sb.toString();//liste l'inventaire avec un objet après le trait
        } else {
            Object obj = game.getPlayer().getItemByName(objectName);
            if (obj == null) {
                return "Vous ne possédez pas cet objet dans votre inventaire.";
            }
            return "Vous inspectez '" + obj.getName() + "' :\n" + obj.getDescription();
        }
    }
}