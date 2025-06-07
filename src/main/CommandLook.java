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
            StringBuilder sb = new StringBuilder();//txt qui va donner les infos de la zone
            sb.append("Vous êtes dans : ").append(currentLocation.getNom()).append("\n")
              .append(currentLocation.getDescription()).append("\nObjets présents : ");

            boolean hasPrintedAny = false;

            if (currentLocation.getItems().isEmpty() && !currentLocation.hasEnigme()) {
                sb.append("aucun");//si ya pas d'objets
            } else {
                for (Object obj : currentLocation.getItems()) {
                    sb.append(obj.getName()).append(" ");// si ya un objet -> nom
                    hasPrintedAny = true;
                }

                if (currentLocation.hasEnigme()) {
                    sb.append("[Énigme]");//si ya une enigme
                }
            }

            return sb.toString();
        } else {
            return "Vous n'êtes dans aucune salle valide.";//si salle null
        }
    }
}