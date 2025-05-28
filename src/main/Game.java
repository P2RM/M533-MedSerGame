package main;
 
import java.util.Scanner;

public class Game {
    private WorldMap map;
    private Player player;
    private CommandRegistry commandRegistry;

    public Game(WorldMap map, Player player, CommandRegistry commandRegistry) {
        this.map = map;
        this.player = player;
        this.commandRegistry = commandRegistry;
        initializeMap();
        initializeCommands();
        map.setPlayerPosition(0, 0);
    }

    private void initializeMap() {
        // Create locations and add to map
    }

    private void initializeCommands() {
        Player player = getPlayer();
    
        commandRegistry.addCommand("help", new CommandHelp("help", "Show available commands"));
        commandRegistry.addCommand("look", new CommandLook("look", "Look around your current location"));
        commandRegistry.addCommand("map", new CommandMap("map", "Show the game map"));
        commandRegistry.addCommand("move", new CommandMove("move", "Move in a direction (w/a/s/d)"));
        commandRegistry.addCommand("inspect", new CommandInspect("inspect", "Inspect an object in your inventory"));
        commandRegistry.addCommand("take", new CommandTake("take", "Pick up an object"));
    }
    
    

    public WorldMap getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to MedSer Game!");
        System.out.println("Type 'help' to see available commands.");
        boolean finished = false;
    
        while (!finished) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
    
            if (input.equals("exit")) {
                System.out.println("Goodbye!");
                finished = true;
            } else {
                String[] parts = input.split(" ", 2); // on sépare en verbe + paramètre
                String verb = parts[0];
                String param = parts.length > 1 ? parts[1] : null;
    
                ICommand command = commandRegistry.getCommands().get(verb);
    
                if (command != null) {
                    // On transmet les paramètres aux commandes qui en ont besoin
                    if (command instanceof CommandMove && param != null) {
                        ((CommandMove) command).setDirection(param);
                    } else if (command instanceof CommandInspect && param != null) {
                        ((CommandInspect) command).setObjectName(param);
                    } else if (command instanceof CommandTake && param != null) {
                        ((CommandTake) command).setObjectName(param);
                    }
                    // Ajouter d'autres commandes si besoin ici...
    
                    String result = command.execute(this);
                    System.out.println(result);
                } else {
                    System.out.println("Unknown command. Type 'help' for a list.");
                }
            }
        }
        scanner.close();
    }
    
}