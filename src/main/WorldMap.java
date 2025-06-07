package main;

public class WorldMap {
    private Location[][] grid;
    private Player player;

    public WorldMap(int width, int height, Player player) {
        grid = new Location[height][width];
        this.player = player;
    }

    public void addLocation(Location loc, int x, int y) {
        grid[y][x] = loc;
    }

    public boolean isInBounds(int x, int y) {// si c dans les limites
        return (y >= 0 && y < grid.length && x >= 0 && x < grid[0].length);
    }

    public Location getLocation(int x, int y) {
        if (!isInBounds(x, y)) return null;
        return grid[y][x];
    }

    public void setPlayerPosition(int x, int y) {
        player.setPosition(x, y);
    }

    public Location getPlayerLocation() {
        return getLocation(player.getPositionX(), player.getPositionY());
    }

    public Location[][] getPrintableGrid() {
        return grid;
    }
}