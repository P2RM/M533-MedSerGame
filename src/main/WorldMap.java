package main;

public class WorldMap {
    private int[][] carte = new int[15][15];
    private boolean locationIsLocked;
    private Location location;

   
    public WorldMap(int[][] carte, boolean locationIsLocked, Location location) {
        this.carte=carte;
        this.locationIsLocked=locationIsLocked;
        this.location=location;
    }

   
    public int getLocation(int row, int column) {
        return carte[row][column];
    }


    public boolean getLocationIsLocked(int row, int column) {
        return locationIsLocked;
    }


    public void addLocation(Location location, int row, int column) {
        this.location = location;
        carte[row][column] = 1; //1 indique que l'espace est utilisé
    }


    public Location getPlayerLocation() {
        return location;
    }

    public void setPlayerLocation(int positionX, int positionY) {
        if (location != null) {
            location = new Location(location.getNom(), location.getDescr(), location.getEtat(), positionX, positionY);
        }
    }
}

