package main;

public class CommandGuess implements ICommand {

    private String verb;
    private String description;
    private String answer; // La réponse proposée par le joueur

    public CommandGuess(String verb, String description) {
        this.verb = verb;
        this.description = description;
        this.answer = null;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String getVerb() { return verb; }

    @Override
    public String getDescription() { return description; }

    @Override
    public String execute(Game game) {
        // L'énigme proposée
        String enonce = 
            "Voici un extrait de code Java :\n" +
            "int x = 5;\n" +
            "int y = 3;\n" +
            "System.out.println(x + y * 2);\n\n" +
            "Quelle sera la valeur affichée à l'écran ?\n" +
            "(Utilise la commande 'guess <réponse>')";

        String bonneReponse = "11"; // 5 + 3*2 = 11

        // Si aucune réponse donnée, on affiche l'énigme
        if (answer == null) {
            return enonce;
        }

        // Si c'est la bonne réponse
        if (answer.trim().equals(bonneReponse)) {
            // Vérifier si la clé a déjà été obtenue
            if (!game.getPlayer().hasItem("clé énigme")) {
                game.getPlayer().addItem(
                    new Cle("clé énigme", "Une clé offerte pour avoir résolu l'énigme du code.", false, "Salle finale")
                );
                return "Bravo ! Tu as résolu l'énigme. Tu reçois une 'clé énigme' dans ton inventaire.";
            } else {
                return "Tu as déjà reçu la clé énigme !";
            }
        } else {
            return "Mauvaise réponse… Essaie encore.";
        }
    }
}