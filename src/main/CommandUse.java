package main;

public class CommandUse implements ICommand {

    private String verb;
    private String description;
    private String objectName;

    public CommandUse(String verb, String description) {
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
            return "Quel objet voulez-vous utiliser ?";
        }

        // check si objet dans l'inventaire
        Object obj = game.getPlayer().getItemByName(objectName);
        if (obj == null) {
            return "Vous n'avez pas cet objet dans votre inventaire.";
        }
        if (!(obj instanceof Cle)) {
            return "Impossible d'utiliser cet objet ici.";
        }
        Cle cle = (Cle) obj;

        // récup la position du joueur
        int x = game.getPlayer().getPositionX();
        int y = game.getPlayer().getPositionY();
        WorldMap map = game.getMap();

        // Vérifie les zones adjacentes pr les ouvrir avec la clé
        int[][] directions = { {1,0}, {-1,0}, {0,1}, {0,-1} };
        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (!map.isInBounds(nx, ny)) continue;//si c ds les limites ça continue
            Location loc = map.getLocation(nx, ny);
            if (loc != null && loc.isLocked() && loc.canUnlock(cle.getName())) {
                loc.unlock();
                game.getPlayer().getInventory().remove(cle);
                return "Vous avez déverrouillé " + loc.getNom() + " avec la clé !";
            }
        }

        // si pas à coté d'une salle vérouillée
        return "Aucune zone à déverrouiller ici avec cette clé.";
    }
}