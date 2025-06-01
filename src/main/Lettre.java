package main;

public class Lettre extends Object {
    private String text;

    public Lettre(String name, String text) {
        super(name, text, false); 
        this.text = text;
    }

    @Override
    public String getDescription() {
        return "Une lettre où il est écrit : " + text;
    }
}