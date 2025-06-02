package main;

public class Enigme {
    private String question;
    private String answer;
    private Object reward;

    public Enigme(String question, String answer, Object reward) {
        this.question = question;
        this.answer = answer.toLowerCase();
        this.reward = reward;
    }

    public String getQuestion() { return question; }
    public boolean isCorrect(String response) {
        return response.trim().toLowerCase().equals(answer);
    }
    public Object getReward() { return reward; }
}

