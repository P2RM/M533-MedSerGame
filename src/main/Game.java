package main;

import java.util.*;

public class Game {

    private WorldMap map;
    private Player player;
    private CommandRegistry commandRegistry;
    private boolean finished;
    private List<String> commandHistory = new ArrayList<>();

    public Game(WorldMap map, Player player, CommandRegistry commandRegistry) {
        this.map = map;
        this.player = player;
        this.commandRegistry = commandRegistry;
        this.finished = false;

        initializeMap();
        initializeCommands();
        map.setPlayerPosition(0, 0);

        // zone de départ = visitée
        player.addLieuVisite(map.getPlayerLocation().getNom());
    }

    
    public List<String> getCommandHistory() { return commandHistory; }

    //s'affiche seulement qd on a le crystal
    private void refreshTeleportCommand() {
        boolean hasCrystal = player.hasItem("teleport crystal");
        boolean alreadyAdded = commandRegistry.getCommand("teleport") != null;
        if (hasCrystal && !alreadyAdded) {
            commandRegistry.addCommand("teleport", new CommandTeleport("teleport", "Se téléporter vers un lieu déjà visité (si crystal possédé)"));
        } else if (!hasCrystal && alreadyAdded) {
           
            commandRegistry.getAllCommands().remove("teleport");
        }
    }

    
    public String processCommand(String input, boolean addToHistory) {
        refreshTeleportCommand(); //se met a jour en fonction de l'avancée

        if (addToHistory) commandHistory.add(input);

        String[] parts = input.trim().toLowerCase().split(" ", 2); //sépare commande et l'argument si yen a un.
        String commandKey = parts[0];// de romain pr pierre : correspond au verb genre "aller" ou "prendre"
        String argument = parts.length > 1 ? parts[1] : null;// si y a une suite genre objet

        ICommand command = commandRegistry.getCommand(commandKey);
        if (command != null) {
            
            if (command instanceof CommandMove && argument != null) {
                ((CommandMove) command).setDirection(argument);
                String result = command.execute(this);
                // si dép réussi on ajoute a lieu visité
                Location curr = map.getPlayerLocation();
                if (curr != null) player.addLieuVisite(curr.getNom());
                return result;
            }
            // pr teleport
            if (command instanceof CommandTeleport && argument != null) {
                ((CommandTeleport) command).setLieuCible(argument);
                String result = command.execute(this);
                Location curr = map.getPlayerLocation();
                if (curr != null) player.addLieuVisite(curr.getNom());
                return result;
            }
           
            if (command instanceof CommandInspect && argument != null)
                ((CommandInspect) command).setObjectName(argument);
            else if (command instanceof CommandTake && argument != null)
                ((CommandTake) command).setObjectName(argument);
            else if (command instanceof CommandUse && argument != null)
                ((CommandUse) command).setObjectName(argument);
            else if (command instanceof CommandGuess && argument != null)
                ((CommandGuess) command).setAnswer(argument);

            return command.execute(this);
        } else {
            return "Commande inconnue.";
        }
    }

    public void run() {//pr le fonctionnement
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Bienvenue dans le MedSerGame !");
        System.out.println("Tapez 'help' pour afficher les commandes disponibles");
        System.out.println("Tapez 'exit' pour quitter le jeu");
        System.out.println("Tapez 'save' pour sauvegarder votre partie en cours");
        System.out.println("Tapez 'load' pour récupérer votre dernière sauvegarde");

        map.setPlayerPosition(player.getPositionX(), player.getPositionY());
        printMap();

        while (!finished) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Merci d’avoir joué !");
                break;
            }

            String result = processCommand(input, true);
            System.out.println(result);
            printMap();
        }
        scanner.close();
    }

    

    private void initializeMap() {
        Location zone0 = new Location("Entrée du donjon", "Une porte massive bloque le passage.", 0, 0);
        Location zone1 = new Location("Salle des parchemins", "Des textes anciens murmurent des secrets.", 0, 1);
        Location zone2 = new Location("Caverne humide", "Des gouttes d'eau tombent du plafond.", 0, 2);
        Location zone3 = new Location("Chambre du gardien", "Un monstre garde un coffre ici.", 0, 3);
        Location zone4 = new Location("Salle verrouillée", "Une salle fermée par un mécanisme secret.", 1, 0);
        Location zone5 = new Location("Corridor sombre", "Il fait noir, vous entendez des bruits...", 1, 1);
        Location zone6 = new Location("Laboratoire", "Des fioles bouillonnent sur les tables.", 1, 2);
        Location zone7 = new Location("Armurerie", "Des armes sont accrochées au mur.", 1, 3);
        Location zone8 = new Location("Bibliothèque", "Des livres anciens et poussiéreux.", 2, 0);
        Location zone9 = new Location("Sortie du donjon", "Une lumière intense vous guide vers la sortie.", 2, 1);
        Location zone10 = new Location("Forge abandonnée", "Des braises éteintes témoignent d'une activité passée.", 2, 2);
        Location zone11 = new Location("Sanctuaire oublié", "Un lieu sacré recouvert de mousse.", 2, 3);
        Location zone12 = new Location("Galerie effondrée", "Le sol est instable, attention où vous marchez.", 3, 0);
        Location zone13 = new Location("Chambre scellée", "Une pièce cachée derrière des symboles.", 3, 1);
        Location zone14 = new Location("Pont suspendu", "Un vieux pont qui grince sous vos pas.", 3, 2);
        Location zone15 = new Location("Observatoire", "Un télescope pointé vers les étoiles.", 3, 3);

        // Objets libres
        zone0.addItem(new Cle("clé rouillée", "Une vieille clé rouillée.", false, "Observatoire"));
        zone3.addItem(new Object("épée émoussée", "Une épée peu tranchante.", false));
        zone7.addItem(new Cle("clé d'or et de platine", "Une clé prestigieuse.", false, "Sortie du donjon"));
        zone3.addItem(new Object("teleport crystal", "Un cristal étrange qui permet de se téléporter.", true));

        // Zones verrouillées
        zone4.lock("clé du savoir");
        zone5.lock("clé du calcul");
        zone9.lock("clé du destin");
        zone15.lock("clé rouillée");

        // Énigmes dans zones accessibles
        zone1.setEnigme(new Enigme(
            "Quel mot de 5 lettres devient plus court quand on y ajoute deux lettres ?",
            "court",
            new Cle("clé du savoir", "Clé gagnée pour avoir bien interprété un jeu de mots.", false, "Salle verrouillée")
        ));

        zone2.setEnigme(new Enigme(
            "Résous : (8 / 2) * (2 + 2)",
            "16",
            new Cle("clé du calcul", "Clé donnée pour une résolution mathématique correcte.", false, "Corridor sombre")
        ));

        zone6.setEnigme(new Enigme(
            "Je suis toujours devant toi mais tu ne peux jamais m’atteindre. Qui suis-je ?",
            "l'avenir",
            new Cle("clé du destin", "Clé mystérieuse liée au futur.", false, "Sortie du donjon")
        ));

        // Ajout des zones à la carte
        map.addLocation(zone0, 0, 0);
        map.addLocation(zone1, 0, 1);
        map.addLocation(zone2, 0, 2);
        map.addLocation(zone3, 0, 3);
        map.addLocation(zone4, 1, 0);
        map.addLocation(zone5, 1, 1);
        map.addLocation(zone6, 1, 2);
        map.addLocation(zone7, 1, 3);
        map.addLocation(zone8, 2, 0);
        map.addLocation(zone9, 2, 1);
        map.addLocation(zone10, 2, 2);
        map.addLocation(zone11, 2, 3);
        map.addLocation(zone12, 3, 0);
        map.addLocation(zone13, 3, 1);
        map.addLocation(zone14, 3, 2);
        map.addLocation(zone15, 3, 3);
    }

    private void initializeCommands() {
        commandRegistry.addCommand("help", new CommandHelp("help", "Affiche la liste des commandes disponibles"));
        commandRegistry.addCommand("look", new CommandLook("look", "Décrit la zone actuelle"));
        commandRegistry.addCommand("map", new CommandMap("map", "Affiche la carte"));
        commandRegistry.addCommand("move", new CommandMove("move", "Se déplacer dans la carte (w/a/s/d)"));
        commandRegistry.addCommand("take", new CommandTake("take", "Ramasser un objet présent"));
        commandRegistry.addCommand("inspect", new CommandInspect("inspect", "Inspecter un objet dans l’inventaire"));
        commandRegistry.addCommand("guess", new CommandGuess("guess", "Résoudre une énigme pour obtenir une clé"));
        commandRegistry.addCommand("use", new CommandUse("use", "Permet d'utiliser une clé pour déverrouiller une zone"));
        commandRegistry.addCommand("save", new CommandSave("save", "Sauvegarde la partie"));
        commandRegistry.addCommand("load", new CommandLoad("load", "Charge la partie sauvegardée"));
        // TELEPORT n’est PAS ajoutée ici, elle l’est dynamiquement selon l’inventaire
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

    public void resetGame() {
        this.map = new WorldMap(4, 4, player);
        this.commandHistory.clear();
        initializeMap();
        map.setPlayerPosition(0, 0);
    }
}