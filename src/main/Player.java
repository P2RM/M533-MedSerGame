package main;
 
import java.util.ArrayList;
import java.util.List;
 
public class Player {
    private String name;
    private int positionX;
    private int positionY;
    private List<Object> inventory;
 
    public Player(String name) {
        this.name = name;
        this.positionX = 0;
        this.positionY = 0;
        this.inventory = new ArrayList<>();
    }
 
    public int getPositionX() {
        return positionX;
    }
 
    public int getPositionY() {
        return positionY;
    }
 
    public void setPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }
 
    public void addToInventory(Object object) {
        inventory.add(object);
    }
 
    public Object getObjectByName(String name) {
        for (Object obj : inventory) {
            if (obj.getName().equalsIgnoreCase(name)) {
                return obj;
            }
        }
        return null;
    }
 
    public List<Object> getInventory() {
        return inventory;
    }
}