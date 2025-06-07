package main;

public class CommandGuess implements ICommand {

    private String verb;
    private String description;
    private String answer;

    public CommandGuess(String verb, String description) {
        this.verb = verb;
        this.description = description;
        this.answer = null;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String getVerb() {
        return verb;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String execute(Game game) {
        Location location = game.getMap().getPlayerLocation();

        // Si ya pas d'énigmes
        if (location == null || !location.hasEnigme()) {
            answer = null; // reset
            return "Il n'y a pas d'énigme ici.";
        }

        Enigme enigme = location.getEnigme();

        // Si ya l'utilisateur ne met pas de réponses
        if (answer == null || answer.isEmpty()) {
            return "Énigme :\n" + enigme.getQuestion();
        }

        // Pour vérifier
        if (enigme.isCorrect(answer)) {
            Object reward = enigme.getReward();

            if (reward != null && !game.getPlayer().hasItem(reward.getName())) {
                game.getPlayer().addItem(reward);
            }

            location.setEnigme(null); // résolution enigme
            answer = null;
            return "Bonne réponse ! Vous obtenez : " + reward.getName();
        } else {
            answer = null;
            return "Mauvaise réponse. Réessayez.";
        }
    }
}