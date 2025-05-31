package main;
import java.util.*;

public class Player {
    private String name;
    private int posX;
    private int posY;
    private List<Object> inventory;

    public Player(String name) {
        this.name = name;
        this.posX = 0;
        this.posY = 0;
        this.inventory = new ArrayList<>();
    }

    
    public int getPositionX() { return posX; }
    public int getPositionY() { return posY; }
    public void setPosition(int x, int y) { this.posX = x; this.posY = y; }
    public List<Object> getInventory() { return inventory; }

    public void addItem(Object item) { inventory.add(item); }
    public boolean hasItem(String itemName) {
        for (Object obj : inventory)
            if (obj.getName().equalsIgnoreCase(itemName)) return true;
        return false;
    }
    public Object getItemByName(String name) {
        for (Object obj : inventory)
            if (obj.getName().equalsIgnoreCase(name)) return obj;
        return null;
    }
}