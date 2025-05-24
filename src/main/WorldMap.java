package main;

public class WorldMap {
    private Location[][] carte;
    private Location playerLocation;

    public WorldMap(int rows, int cols) {
        this.carte = new Location[rows][cols];
    }

    public void addLocation(Location location, int row, int column) {
        carte[row][column] = location;
    }

    public Location getLocation(int row, int column) {
        return carte[row][column];
    }

    public boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < carte.length && y < carte[0].length;
    }

    public Location getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerPosition(int x, int y) {
        this.playerLocation = getLocation(x, y);
    }

    public String[][] getPrintableGrid() {
        String[][] grid = new String[carte.length][carte[0].length];
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[0].length; j++) {
                grid[i][j] = (carte[i][j] == null) ? " " : "O";
            }
        }
        return grid;
    }
}
