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


 

}
