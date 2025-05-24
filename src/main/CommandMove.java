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
    public String getVerb() {
        return this.verb;
    }
 
    @Override
    public String getDescription() {
        return this.description;
    }
 
    @Override
    public String execute(Game game) {
        List<String> validDirections = Arrays.asList("w", "a", "s", "d"); //transformer en ArrayList le String
 
        if (direction == null || !validDirections.contains(direction)) {
            return "Unknown direction.";
        }
 
        int x = game.getPlayer().getPositionX();
        int y = game.getPlayer().getPositionY();
        int newX = x;
        int newY = y;
 
        switch (direction) {
            case "w":
                newY--;
                break;
            case "s":
                newY++;
                break;
            case "a":
                newX--;
                break;
            case "d":
                newX++;
                break;
        }
 
        if (!game.getMap().isInBounds(newX, newY)) { //si c dans les limites
            return "Impossible to move there.";
        }
 
        Location target = game.getMap().getLocation(newX, newY);
        if (target == null) {
            return "There is nothing in that direction.";
        }
 
        if (target.isLocked()) {
            return "Zone locked.";
        }
 
        game.getMap().setPlayerPosition(newX, newY);
        return target.getDescription();
    }
}