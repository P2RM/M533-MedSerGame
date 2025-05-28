package main;

import java.util.ArrayList;
import java.util.List;
import utils.IPrintable;

public class Location implements IPrintable {

    private String nom;
    private String descr;
    private boolean locked;
    private int positionX;
    private int positionY;
    private List<Object> objects;

    public Location(String nom, String descr, int positionX, int positionY) {
        this.nom = nom;
        this.descr = descr;
        this.positionX = positionX;
        this.positionY = positionY;
        this.locked = false; // Par défaut déverrouillé
        this.objects = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public String getDescr() {
        return descr;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void lockLocation() {
        this.locked = true;
    }

    public void unlockLocation() {
        this.locked = false;
    }

    public void addObject(Object obj) {
        this.objects.add(obj);
    }

    public Object removeObjectByName(String name) {
        for (Object obj : objects) {
            if (obj.getName().equalsIgnoreCase(name)) {
                objects.remove(obj);
                return obj;
            }
        }
        return null;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public String getDescription() {
        return descr;
    }

    @Override
    public String getPrintableString() {
        return locked ? "X" : "O";
    }

    @Override
    public boolean isGrayedOut() {
        return false;
    }
}