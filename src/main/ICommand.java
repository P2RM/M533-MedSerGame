package main;

public interface ICommand {
    String getVerb();
    String getDescription();
    String execute(Game game);
}