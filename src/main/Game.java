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

        initializeMap();
        initializeCommands();
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

        // Objets
        zone0.addItem(new Cle("clé rouillée", "Une vieille clé rouillée.", false, "Salle secrète"));
        zone2.addItem(new Object("potion étrange", "Une fiole mystérieuse.", false));
        zone3.addItem(new Object("épée émoussée", "Une épée peu tranchante.", false));

        zone4.lock("clé rouillée");

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
        commandRegistry.addCommand("help", new CommandHelp("help", "Affiche la liste des commandes disponibles"));
        commandRegistry.addCommand("look", new CommandLook("look", "Décrit la zone actuelle"));
        commandRegistry.addCommand("map", new CommandMap("map", "Affiche la carte"));
        commandRegistry.addCommand("move", new CommandMove("move", "Se déplacer dans la carte (w/a/s/d)"));
        commandRegistry.addCommand("take", new CommandTake("take", "Ramasser un objet présent"));
        commandRegistry.addCommand("inspect", new CommandInspect("inspect", "Inspecter un objet dans l’inventaire"));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans le MedSer Game !");
        System.out.println("Tapez 'help' pour afficher les commandes disponibles.\n");

        map.setPlayerPosition(player.getPositionX(), player.getPositionY());
        printMap();

        while (!finished) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Merci d’avoir joué !");
                break;
            }
            String[] parts = input.split(" ", 2);
            String commandKey = parts[0];
            String argument = parts.length > 1 ? parts[1] : null;

            ICommand command = commandRegistry.getCommand(commandKey);
            if (command != null) {
                if (command instanceof CommandMove && argument != null) {
                    ((CommandMove) command).setDirection(argument);
                } else if (command instanceof CommandInspect && argument != null) {
                    ((CommandInspect) command).setObjectName(argument);
                } else if (command instanceof CommandTake && argument != null) {
                    ((CommandTake) command).setObjectName(argument);
                }

                String result = command.execute(this);
                System.out.println(result);
                printMap();
            } else {
                System.out.println("Commande inconnue. Tapez 'help' pour les commandes disponibles.");
            }
        }
        scanner.close();
    }

    public void printMap() {
        System.out.println("\n--- Carte du Monde ---");
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
            System.out.println("Vous êtes en (" + player.getPositionX() + ", " + player.getPositionY() + ") - " + current.getNom());
        } else {
            System.out.println("Position actuelle : (" + player.getPositionX() + ", " + player.getPositionY() + ")");
        }
    }

    public WorldMap getMap() { return map; }
    public Player getPlayer() { return player; }
    public CommandRegistry getCommandRegistry() { return commandRegistry; }
    public void setFinished(boolean finished) { this.finished = finished; }
}