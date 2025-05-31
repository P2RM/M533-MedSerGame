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
        return verb;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String execute(Game game) {
        Location currentLocation = game.getMap().getPlayerLocation();
        if (currentLocation != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Vous êtes dans : ").append(currentLocation.getNom()).append("\n")
              .append(currentLocation.getDescription()).append("\nObjets présents : ");
            if (currentLocation.getItems().isEmpty()) {
                sb.append("aucun");
            } else {
                for (Object obj : currentLocation.getItems()) {
                    sb.append(obj.getName()).append(" ");
                }
            }
            return sb.toString();
        } else {
            return "Vous n'êtes dans aucune salle valide.";
        }
    }
}