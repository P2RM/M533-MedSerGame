package main;

import java.util.List;
import utils.IPrintable;
 
public class Location implements IPrintable {
    private String nom;
    private String descr;
    private boolean etat = false; // false = unlocked
    private int positionX;
    private int positionY;
    private List<Object> objects;
 
    public Location(String nom, String descr, int positionX, int positionY) {
        this.nom = nom;
        this.descr = descr;
        this.positionX = positionX;
        this.positionY = positionY;}

 
    public String getNom() {
        return this.nom;
    }
 
    public String getDescr() {
        return this.descr;
    }
 
    public boolean getEtat() {
        return this.etat;
    }
 
    public int getPositionX() {
        return this.positionX;
    }
 
    public int getPositionY() {
        return this.positionY;
    }
 
    public void lockLocation() {
        etat = true;
    }
 
    public void unlockLocation() {
        etat = false;
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
 
    @Override
    public String getPrintableString() {
        return etat ? "X" : "O";
    }
 
    @Override
    public boolean isGrayedOut() {
        return false;
    }
 
    public boolean isLocked() {
        return etat;
    }
 
    public String getDescription() {
        return descr;
    }
}