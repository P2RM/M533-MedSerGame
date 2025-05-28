package main;

public class Player {
    private String name;
    private int positionX;
    private int positionY;

    public Player(String name) {
        this.name = name;
        this.positionX = 0;
        this.positionY = 0;
    }

    public Player(String name, int startX, int startY) {
        this.name = name;
        this.positionX = startX;
        this.positionY = startY;
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

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getName() {
        return name;
    }
}