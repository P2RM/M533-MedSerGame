package main;

public class Main {
    public static void main(String[] args) {
        // Création du joueur et de la carte
        Player player = new Player("Romain");
        WorldMap map = new WorldMap(5, 5, player);
        CommandRegistry commandRegistry = new CommandRegistry();

        // Enregistrement des commandes
        commandRegistry.addCommand("help", new CommandHelp("help", "Affiche la liste des commandes disponibles"));
        commandRegistry.addCommand("move", new CommandMove("move", "Déplace le joueur dans une direction"));
        commandRegistry.addCommand("take", new CommandTake("take", "Ramasse un objet présent dans la pièce"));
        commandRegistry.addCommand("inspect", new CommandInspect("inspect", "Permet d’inspecter un objet dans l’inventaire ou dans la pièce"));

        // Création et exécution du jeu
        Game game = new Game(map, player, commandRegistry);
        game.run();
    }
}