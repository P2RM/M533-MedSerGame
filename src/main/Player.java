package main;
import java.util.*;
import utils.TextUtils;

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
    public boolean hasItem(String itemName) { //vérifie si l'objet est dans notre inventaire.
        String search = TextUtils.normalize(itemName);
        for (Object obj : inventory)
            if (TextUtils.normalize(obj.getName()).equals(search)) return true;
        return false;
    }
    public Object getItemByName(String name) {
        String search = TextUtils.normalize(name);
        for (Object obj : inventory)
            if (TextUtils.normalize(obj.getName()).equals(search)) return obj;
        return null;
    }
    private Set<String> lieuxVisites = new HashSet<>();

public void addLieuVisite(String nom) {
    lieuxVisites.add(nom.toLowerCase());
}
public boolean aVisite(String nom) {
    return lieuxVisites.contains(nom.toLowerCase());
}
public Set<String> getLieuxVisites() {
    return lieuxVisites;
}
}