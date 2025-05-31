package main;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Romain");
        WorldMap map = new WorldMap(5, 5, player);
        CommandRegistry commandRegistry = new CommandRegistry();

        // Création et exécution du jeu
        Game game = new Game(map, player, commandRegistry);
        game.run();
    }
}