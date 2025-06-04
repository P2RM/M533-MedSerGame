package main;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Romain");
        WorldMap map = new WorldMap(4, 4, player);
        CommandRegistry commandRegistry = new CommandRegistry();

        // Enregistrement de toutes les commandes
        commandRegistry.addCommand("help", new CommandHelp("help", "Affiche la liste des commandes disponibles"));
        commandRegistry.addCommand("move", new CommandMove("move", "Déplace le joueur dans une direction"));
        commandRegistry.addCommand("take", new CommandTake("take", "Ramasse un objet présent dans la pièce"));
        commandRegistry.addCommand("inspect", new CommandInspect("inspect", "Permet d’inspecter un objet dans l’inventaire ou dans la pièce"));
        commandRegistry.addCommand("use", new CommandUse("use", "Permet d'utiliser une clé récupérée pour déverrouiller une zone"));
        commandRegistry.addCommand("guess", new CommandGuess("guess", "Résoudre une énigme pour obtenir une clé"));
        commandRegistry.addCommand("map", new CommandMap("map", "Affiche la carte"));

        Game game = new Game(map, player, commandRegistry);
        game.run();
    }
}