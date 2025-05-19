package main;

public interface ICommand {
    
    public String getVerb();
    public String getDescription();
    public String execute(Game game);
}