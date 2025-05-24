package main;

public class CommandMap implements ICommand {

    private String verb;
    private String description;

    public CommandMap(String verb, String description) {
        this.verb = verb;
        this.description = description;
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
        int playerX = game.getPlayer().getPositionX();
        int playerY = game.getPlayer().getPositionY();

        /*System.out.println(
            Array2DPrinter.print2DArray(
                game.getMap().getPrintableGrid(),
                playerY,
                playerX
            )
        );
        */return "";
    }
}
