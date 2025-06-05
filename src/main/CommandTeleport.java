package main;

public class CommandTeleport implements ICommand {

    private String verb;
    private String description;
    private String lieuCible;

    public CommandTeleport(String verb, String description) {
        this.verb = verb;
        this.description = description;
        this.lieuCible = null;
    }

    public void setLieuCible(String lieu) {
        this.lieuCible = lieu;
    }

    @Override
    public String getVerb() { return verb; }

    @Override
    public String getDescription() { return description; }

    @Override
    public String execute(Game game) {
        // 1. Le crystal doit être possédé
        if (!game.getPlayer().hasItem("teleport crystal")) {
            return "Vous n'avez pas le teleport crystal ! Impossible de vous téléporter.";
        }
        // 2. Argument : nom du lieu
        if (lieuCible == null || lieuCible.isEmpty()) {
            return "Utilisation : teleport nom-du-lieu";
        }
        // 3. Vérifier si le lieu a été visité
        if (!game.getPlayer().aVisite(lieuCible)) {
            return "Vous n'avez pas encore visité ce lieu. Impossible de s'y téléporter.";
        }
        // 4. Chercher le lieu sur la map
        Location destination = null;
        for (Location[] row : game.getMap().getPrintableGrid()) {
            for (Location loc : row) {
                if (loc != null && loc.getNom().equalsIgnoreCase(lieuCible)) {
                    destination = loc;
                    break;
                }
            }
            if (destination != null) break;
        }
        if (destination == null) {
            return "Ce lieu n'existe pas.";
        }
        // 5. Déplacer le joueur
        game.getPlayer().setPosition(destination.getX(), destination.getY());
        // Marquer la salle comme visitée à nouveau
        game.getPlayer().addLieuVisite(destination.getNom());
        return "Téléportation réussie ! Vous êtes maintenant dans : " + destination.getNom() + ".\n" + destination.getDescription();
    }
}