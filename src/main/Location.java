package main;

public class Location {

    private String nom;
    private String descr;
    private boolean locked;

    public Location(String nom, String descr, boolean locked) {
        this.nom = nom;
        this.descr = descr;
        this.locked = locked;
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
}
