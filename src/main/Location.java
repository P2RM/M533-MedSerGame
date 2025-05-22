package main;

public class Location implements IPrintable {
    private String nom;
    private String descr;
    private boolean etat;
    private int positionX;
    private int positionY;

    public Location(String nom, String descr, boolean etat, int positionX, int positionY){
        this.nom=nom;
        this.descr=descr;
        this.etat=etat;
        this.positionX=positionX;
        this.positionY=positionY;
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
        etat=true;
    }

    public void unlockLocation(){
        etat=false;
    }

    @Override
    public String getPrintableString() {
        if (isLocked) return "X";        // verrouillé
        else if (isVisited) return "O";  // déjà visité
        else return " ";                // accessible
    }

    @Override
    public boolean isGrayedOut() {
        return !isVisited; //griser si jamais visité
    }

}
