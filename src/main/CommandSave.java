package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CommandSave implements ICommand {
    private String verb;
    private String description;

    public CommandSave(String verb, String description) {
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
        List<String> history = game.getCommandHistory();
        //code du prof adapté
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILENAME))) {
            for (String line : history) {
                writer.write(line);
                writer.newLine();
            }
            return "Partie sauvegardée dans " + SAVE_FILENAME;
        } catch (IOException e) {
            return "Erreur lors de la sauvegarde : " + e.getMessage();//renvoies le message qui correpond à l'erreur
        }
    }
}