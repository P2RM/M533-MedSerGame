package main;
import java.util.Arrays;
import java.util.List;

public class CommandMove implements ICommand {

    private String verb;
    private String description;
    private String direction;

    public CommandMove(String verb, String description) {
        this.verb = verb;
        this.description = description;
    }

    public void setDirection(String direction) {
        this.direction = direction.toLowerCase();
    }

    @Override
    public String getVerb() { return verb; }
    @Override
    public String getDescription() { return description; }

    @Override
    public String execute(Game game) {
        List<String> validDirections = Arrays.asList("w", "a", "s", "d");
        if (direction == null || !validDirections.contains(direction)) {
            return "Utilisez 'move w', 'move a', 'move s' ou 'move d' (haut, gauche, bas, droite).";
        }
        int x = game.getPlayer().getPositionX();
        int y = game.getPlayer().getPositionY();
        int newX = x, newY = y;
        switch (direction) {
            case "w": newY--; break;
            case "s": newY++; break;
            case "a": newX--; break;
            case "d": newX++; break;
        }
        if (!game.getMap().isInBounds(newX, newY)) return "Impossible d'aller là.";
        Location target = game.getMap().getLocation(newX, newY);
        if (target == null) return "Il n'y a rien dans cette direction.";
        if (target.isLocked()) return "Zone verrouillée. Trouvez une clé.";
        game.getPlayer().setPosition(newX, newY);
        return target.getDescription();
    }
}