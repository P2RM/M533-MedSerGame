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

    public void setLieuCible(String lieu) {//lieu sur lequel on va
        this.lieuCible = lieu;
    }

    @Override
    public String getVerb() { return verb; }

    @Override
    public String getDescription() { return description; }

    @Override
    public String execute(Game game) {
        //faut avoir le crystal
        if (!game.getPlayer().hasItem("teleport crystal")) {
            return "Vous n'avez pas le teleport crystal ! Impossible de vous téléporter.";
        }
        
        if (lieuCible == null || lieuCible.isEmpty()) {//nom du lieu
            return "Utilisation : teleport nom-du-lieu";
        }
        //Vérif si lieu a été visité
        if (!game.getPlayer().aVisite(lieuCible)) {
            return "Vous n'avez pas encore visité ce lieu. Impossible de s'y téléporter.";
        }
        //chercher le lieu sur la map
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
        
        game.getPlayer().setPosition(destination.getX(), destination.getY());//le joueur se déplace
        // salle = visitée à nouveau
        game.getPlayer().addLieuVisite(destination.getNom());
        return "Téléportation réussie ! Vous êtes maintenant dans : " + destination.getNom() + ".\n" + destination.getDescription();
    }
}