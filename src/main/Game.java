package main;

import java.util.Scanner;

public class Game {

    private WorldMap map;
    private Player player;
    private CommandRegistry commandRegistry;
    private boolean finished;

    public Game(WorldMap map, Player player, CommandRegistry commandRegistry) {
        this.map = map;
        this.player = player;
        this.commandRegistry = commandRegistry;
        this.finished = false;

        initializeMap();       // Ajout des zones codées en dur
        initializeCommands();  // Ajout des commandes disponibles
        map.setPlayerPosition(0, 0);
    }

    private void initializeMap() {
        Location zone0 = new Location("Entrée du donjon", "Une porte massive bloque le passage.", 0, 0);
        Location zone1 = new Location("Salle des énigmes", "Une pièce remplie de parchemins.", 0, 1);
        Location zone2 = new Location("Caverne humide", "Des gouttes d'eau tombent du plafond.", 0, 2);
        Location zone3 = new Location("Chambre du gardien", "Un monstre garde un coffre ici.", 1, 0);
        Location zone4 = new Location("Salle secrète", "Une salle bien cachée derrière un mur.", 1, 1);
        Location zone5 = new Location("Corridor sombre", "Il fait noir, vous entendez des bruits...", 1, 2);
        Location zone6 = new Location("Laboratoire", "Des fioles bouillonnent sur les tables.", 2, 0);
        Location zone7 = new Location("Armurerie", "Des armes sont accrochées au mur.", 2, 1);
        Location zone8 = new Location("Bibliothèque", "Des livres anciens et poussiéreux.", 2, 2);
        Location zone9 = new Location("Sortie du donjon", "Une lumière intense vous guide vers la sortie.", 3, 1);

        map.addLocation(zone0, 0, 0);
        map.addLocation(zone1, 0, 1);
        map.addLocation(zone2, 0, 2);
        map.addLocation(zone3, 1, 0);
        map.addLocation(zone4, 1, 1);
        map.addLocation(zone5, 1, 2);
        map.addLocation(zone6, 2, 0);
        map.addLocation(zone7, 2, 1);
        map.addLocation(zone8, 2, 2);
        map.addLocation(zone9, 3, 1);
    }

    private void initializeCommands() {
        commandRegistry.addCommand("help", new CommandHelp("help", "List all available commands"));
        commandRegistry.addCommand("look", new CommandLook("look", "Describe the current zone"));
        commandRegistry.addCommand("map", new CommandMap("map", "Display the world map"));
        commandRegistry.addCommand("move", new CommandMove("move", "Move to a new location"));
        commandRegistry.addCommand("inspect", new CommandInspect("inspect", "Inspect an object in your inventory"));
        commandRegistry.addCommand("take", new CommandTake("take", "Pick up an object"));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the MedSer Game!");
        System.out.println("Type 'help' to see available commands.\n");

        map.setPlayerPosition(player.getPositionX(), player.getPositionY());
        printMap();

        while (!finished) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Goodbye!");
                finished = true;
            } else {
                String[] parts = input.split(" ", 2);
                String verb = parts[0];
                String param = parts.length > 1 ? parts[1] : null;

                ICommand command = commandRegistry.getCommands().get(verb);

                if (command != null) {
                    if (command instanceof CommandMove && param != null) {
                        ((CommandMove) command).setDirection(param);
                    } else if (command instanceof CommandInspect && param != null) {
                        ((CommandInspect) command).setObjectName(param);
                    } else if (command instanceof CommandTake && param != null) {
                        ((CommandTake) command).setObjectName(param);
                    }

                    String result = command.execute(this);
                    System.out.println(result);
                    printMap();
                } else {
                    System.out.println("Unknown command. Type 'help' for a list.");
                }
            }
        }

        scanner.close();
    }

    public void printMap() {
        System.out.println("\n--- World Map ---");

        Location[][] grid = map.getPrintableGrid();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (x == player.getPositionX() && y == player.getPositionY()) {
                    System.out.print(" R ");
                } else {
                    Location loc = grid[y][x];
                    if (loc == null) {
                        System.out.print(" . ");
                    } else {
                        System.out.print(" " + loc.getPrintableString() + " ");
                    }
                }
            }
            System.out.println();
        }

        Location current = map.getPlayerLocation();
        if (current != null) {
            System.out.println("You are at position (" + player.getPositionX() + ", " + player.getPositionY() + ") - " + current.getNom());
        } else {
            System.out.println("You are at position (" + player.getPositionX() + ", " + player.getPositionY() + ")");
        }
    }

    // Getters et Setters
    public WorldMap getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}