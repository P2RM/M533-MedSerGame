package main;

public class WorldMap {

    private Location[][] carte;
    private Player player;

    public WorldMap(int rows, int cols, Player player) {
        this.carte = new Location[rows][cols]; // [ligne][colonne] = [y][x]
        this.player = player;
    }

    public boolean isInBounds(int x, int y) {
        return y >= 0 && y < carte.length && x >= 0 && x < carte[0].length;
    }

    public Location getLocation(int x, int y) {
        if (!isInBounds(x, y)) return null;
        return carte[y][x];
    }

    public void addLocation(Location location, int y, int x) {
        if (isInBounds(x, y)) {
            carte[y][x] = location;
        }
    }

    public void setPlayerPosition(int x, int y) {
        if (isInBounds(x, y)) {
            player.setPositionX(x);
            player.setPositionY(y);
        }
    }

    public Location getPlayerLocation() {
        return getLocation(player.getPositionX(), player.getPositionY());
    }

    public int getPlayerX() {
        return player.getPositionX();
    }

    public int getPlayerY() {
        return player.getPositionY();
    }
}