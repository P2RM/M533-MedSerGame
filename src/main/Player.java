package main;

public class Player {

    private int positionX;
    private int positionY;

    public Player(int startX, int startY) {
        this.positionX = startX;
        this.positionY = startY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
