package main;

import java.util.ArrayList;
import java.util.List;

public class Location {

    private String nom;
    private String descr;
    private boolean locked;
    private List<Object> objects; 

    public Location(String nom, String descr, boolean locked) {
        this.nom = nom;
        this.descr = descr;
        this.locked = locked;
        this.objects = new ArrayList<>(); 
    }

    public String getNom() {
        return nom;
    }

    public String getDescr() {
        return descr;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void addObject(Object object) {
        objects.add(object);
    }

    public List<Object> getObjects() {
        return objects;
    }

    public Object removeObjectByName(String objectName) {
        for (Object obj : objects) {
            if (obj.getName().equalsIgnoreCase(objectName)) {
                objects.remove(obj);
                return obj;
            }
        }
        return null;
    }
}