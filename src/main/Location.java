package main;
import java.util.*;
import utils.IPrintable;

public class Location implements IPrintable {
    private String nom;
    private String description;
    private int x, y;
    private List<Object> items;
    private boolean locked;
    private String requiredKey;
    
    // Nouveau : une vraie énigme
    private Enigme enigme;

    public Location(String nom, String description, int x, int y) {
        this.nom = nom;
        this.description = description;
        this.x = x;
        this.y = y;
        this.items = new ArrayList<>();
        this.locked = false;
    }

    public void addItem(Object item) { items.add(item); }

    public Object getItemByName(String name) {
        for (Object obj : items) {
            if (obj.getName().equalsIgnoreCase(name)) return obj;
        }
        return null;
    }

    public boolean removeItem(Object item) { return items.remove(item); }

    public boolean removeItemByName(String name) {
        Object obj = getItemByName(name);
        if (obj != null) { items.remove(obj); return true; }
        return false;
    }

    public List<Object> getItems() { return items; }

    public void lock(String key) { this.locked = true; this.requiredKey = key; }

    public boolean isLocked() { return locked; }

    public boolean canUnlock(String key) {
        return locked && requiredKey != null && requiredKey.equalsIgnoreCase(key);
    }

    public void unlock() { this.locked = false; this.requiredKey = null; }

    public String getNom() { return nom; }

    public String getDescription() { return description; }

    public int getX() { return x; }

    public int getY() { return y; }

    @Override
    public String getPrintableString() { return locked ? "X" : "O"; }

    @Override
    public boolean isGrayedOut() { return false; }

    public boolean hasEnigme() { return enigme != null; }

    public void setEnigme(Enigme enigme) { this.enigme = enigme; }

    public Enigme getEnigme() { return enigme; }
}