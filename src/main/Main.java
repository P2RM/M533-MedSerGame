package main;

public class Main {
    public static void main(String[] args) {

        WorldMap map = new WorldMap(10, 10);
        Player player = new Player("Romain");
        CommandRegistry commandRegistry = new CommandRegistry();
        Game game = new Game(map, player, commandRegistry);


        game.run();
    }
}
