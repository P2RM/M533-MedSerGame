package main;

import java.util.List;

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
            StringBuilder sb = new StringBuilder(); //txt qui va donner les infos de la zone
            sb.append("Vous êtes dans : ").append(currentLocation.getNom()).append("\n")
              .append(currentLocation.getDescription()).append("\nObjets présents : ");

            if (currentLocation.getItems().isEmpty() && !currentLocation.hasEnigme()) {
                sb.append("aucun");
            } else {
                List<Object> items = currentLocation.getItems();
                if (!items.isEmpty()) {
                    for (int i = 0; i < items.size(); i++) {
                        sb.append(items.get(i).getName());
                        if (i < items.size() - 1) {
                            sb.append(" / ");
                        }
                    }
                }
                if (currentLocation.hasEnigme()) {
                    if (!items.isEmpty()) sb.append(" / ");
                    sb.append("[Énigme]");
                }
            }

            return sb.toString();
        } else {
            return "Vous n'êtes dans aucune salle valide.";
        }
    }
}