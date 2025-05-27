package main;
 
public class Lettre extends Object {
    private String text;
 
    public Lettre(String name, String text) {
        super(name, text, usable);
        this.text = text;
    }
 
    @Override
    public String getDescription() {
        return "A letter that reads: " + text;
    }
}