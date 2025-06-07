package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CommandLoad implements ICommand {
    private String verb;
    private String description;

    public CommandLoad(String verb, String description) {
        this.verb = verb;
        this.description = description;
    }

    @Override
    public String getVerb() { return verb; }
    @Override
    public String getDescription() { return description; }

    @Override
    public String execute(Game game) {
        String SAVE_FILENAME = "save.txt";
        try {
            List<String> lignes = Files.readAllLines(Paths.get(SAVE_FILENAME));
            game.resetGame();
            for (String ligne : lignes) {
                if (!ligne.trim().isEmpty()) {
                    game.processCommand(ligne, false); // si c'est faux, ça ajoute pas à l'historique
                }
            }
            return "Partie chargée depuis " + SAVE_FILENAME;
        } catch (IOException e) {
            return "Impossible de charger la sauvegarde : " + e.getMessage();
        }
    }
}