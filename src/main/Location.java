package main;
import utils.IPrintable;

public class Location implements IPrintable {
    private String nom;
    private String descr;
    private boolean etat = false; // false = unlocked
    private int positionX;
    private int positionY;

    public Location(String nom, String descr, int positionX, int positionY){
        this.nom = nom;
        this.descr = descr;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public String getNom(){
        return this.nom;
    }

    public String getDescr(){
        return this.descr;
    }

    public boolean getEtat(){
        return this.etat;
    }

    public int getPositionX(){
        return this.positionX;
    }

    public int getPositionY(){
        return this.positionY;
    }

    public void lockLocation(){
        etat = true;
    }

    public void unlockLocation(){
        etat = false;
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
