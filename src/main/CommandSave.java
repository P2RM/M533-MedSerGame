package main;
import java.io.*;

public class CommandSave implements ICommand {
    private String verb;
    private String description;
    public CommandSave(String verb, String description) {
        this.verb = verb; this.description = description;
    }
    @Override public String getVerb() { return verb; }
    @Override public String getDescription() { return description; }
    @Override
    public String execute(Game game) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("save.txt"))) {
            for (String cmd : game.getCommandHistory()) {
                writer.println(cmd);
            }
            return "Partie sauvegardée avec succès !";
        } catch (IOException e) {
            return "Erreur lors de la sauvegarde.";
        }
    }
}