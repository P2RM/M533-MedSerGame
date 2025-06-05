package main;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Romain");
        WorldMap map = new WorldMap(4, 4, player);
        CommandRegistry commandRegistry = new CommandRegistry();
        commandRegistry.addCommand("help", new CommandHelp("help", "Affiche la liste des commandes disponibles"));
        commandRegistry.addCommand("move", new CommandMove("move", "Déplace le joueur dans une direction"));
        commandRegistry.addCommand("take", new CommandTake("take", "Ramasse un objet présent dans la pièce"));
        commandRegistry.addCommand("inspect", new CommandInspect("inspect", "Permet d’inspecter un objet dans l’inventaire ou dans la pièce"));
        commandRegistry.addCommand("use", new CommandUse("use", "Permet d'utiliser une clé récupérée pour déverrouiller une zone"));
        commandRegistry.addCommand("guess", new CommandGuess("guess", "Résoudre une énigme pour obtenir une clé"));
        commandRegistry.addCommand("map", new CommandMap("map", "Affiche la carte"));
        commandRegistry.addCommand("save", new CommandSave("save", "Sauvegarde la partie"));

        Game game = new Game(map, player, commandRegistry);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Tapez \"1\" pour une nouvelle partie\nTapez \"2\" pour charger la dernière sauvegarde");
        String choix = scanner.nextLine().trim();

        if (choix.equals("2")) {
            // Recharge toutes les commandes sauvegardées AVANT de démarrer la boucle joueur
            try (BufferedReader reader = new BufferedReader(new FileReader("save.txt"))) {
                String ligne;
                while ((ligne = reader.readLine()) != null) {
                    game.processCommand(ligne, false); // exécute sans ajouter à l'historique !
                }
                System.out.println("Partie chargée.");
            } catch (IOException e) {
                System.out.println("Aucune sauvegarde trouvée. Nouvelle partie.");
            }
        }

        game.run();
    }
}