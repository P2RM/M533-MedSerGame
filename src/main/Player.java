package main;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int positionX;
    private int positionY;
    private List<Object> inventaire = new ArrayList<>();

    public Player(String name) {
        this.name = name;
        this.positionX = 0;
        this.positionY = 0;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public List<Object> getInventory() {
    return inventaire;
}


    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getName() {
        return name;
    }

    public void addToInventory(Object object) {
    for (Object obj : inventaire) {
        if (obj.getName().equalsIgnoreCase(object.getName())) {
            return; // pour Pierre de Romain : c'est en gros comme si l'objet existe déjà
        }
    }
    inventaire.add(object);
}

}