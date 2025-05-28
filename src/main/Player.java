package main;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int positionX;
    private int positionY;
    private List<GameObject> inventory; // Liste des objets ramassés

    public Player(int startX, int startY) {
        this.positionX = startX;
        this.positionY = startY;
        this.inventory = new ArrayList<>();
    }

    // Position du joueur
    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    // Inventaire
    public void addObject(GameObject obj) {
        inventory.add(obj);
    }

    
    
    public boolean hasObject(String objectName) {
        for (GameObject obj : inventory) {
            if (obj.getName().equalsIgnoreCase(objectName)) {
                return true;
            }
        }
        return false;
    }

    public GameObject getObject(String objectName) {
        for (GameObject obj : inventory) {
            if (obj.getName().equalsIgnoreCase(objectName)) {
                return obj;
            }
        }
        return null;
    }

    public List<GameObject> getInventory() {
        return inventory;
    }
}