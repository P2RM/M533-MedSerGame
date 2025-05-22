package main;
 
public class Game {
 
    private WorldMap map;
    private Player player;
    private CommandRegistry commandRegistry;
 
    public Game() {
        this.map = new WorldMap(5, 5);
        this.player = new Player("Player");
        this.commandRegistry = new CommandRegistry();
        initializeMap();
        initializeCommands();
        map.setPlayerPosition(0, 0);
    }
 
    private void initializeMap() {
    Location zone0 = new Location("Entrée du donjon", "Une porte massive bloque le passage.", 0, 0);
    Location zone1 = new Location("Salle des énigmes", "Une pièce remplie de parchemins.", 0, 1);
    Location zone2 = new Location("Caverne humide", "Des gouttes d'eau tombent du plafond.", 0, 2);
    Location zone3 = new Location("Chambre du gardien", "Un monstre garde un coffre ici.", 1, 0);
    Location zone4 = new Location("Salle secrète", "Une salle bien cachée derrière un mur.", 1, 1);
    Location zone5 = new Location("Corridor sombre", "Il fait noir, vous entendez des bruits...", 1, 2);
    Location zone6 = new Location("Laboratoire", "Des fioles bouillonnent sur les tables.", 2, 0);
    Location zone7 = new Location("Armurerie", "Des armes sont accrochées au mur.", 2, 1);
    Location zone8 = new Location("Bibliothèque", "Des livres anciens et poussiéreux.", 2, 2);
    Location zone9 = new Location("Sortie du donjon", "Une lumière intense vous guide vers la sortie.", 3, 1);
 
        map.addLocation(zone0, 0, 0);
        map.addLocation(zone1, 0, 1);
        map.addLocation(zone2, 0, 2);
        map.addLocation(zone3, 1, 0);
        map.addLocation(zone4, 1, 1);
        map.addLocation(zone5, 1, 2);
        map.addLocation(zone6, 2, 0);
        map.addLocation(zone7, 2, 1);
        map.addLocation(zone8, 2, 2);
        map.addLocation(zone9, 3, 1);
    }
 
    private void initializeCommands() {
        commandRegistry.addCommand("help", new CommandHelp("help", "List all available commands"));
        commandRegistry.addCommand("look", new CommandLook("look", "Describe the current zone"));
        commandRegistry.addCommand("map", new CommandMap("map", "Display the world map"));
        commandRegistry.addCommand("move", new CommandMove("move", "Move to a new zone"));
    }
 
    public WorldMap getMap() {
        return map;
    }
 
    public Player getPlayer() {
        return player;
    }
 
    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }
 
    public void run() {
        public void run() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to MedSer Game!");
    System.out.println("Type 'help' to see available commands.");
 
    boolean running = true;
 
    while (running) {
        System.out.print("> ");
        String input = scanner.nextLine().trim().toLowerCase();
 
        if (input.equals("exit")) {
            System.out.println("Goodbye!");
            running = false;
        } else if (commandRegistry.getCommands().containsKey(input)) {
            ICommand command = commandRegistry.getCommands().get(input);
            String result = command.execute(this);
            System.out.println(result);
        } else {
            System.out.println("Unknown command. Type 'help' for a list.");
        }
    }
 
    scanner.close();
}
    }
}