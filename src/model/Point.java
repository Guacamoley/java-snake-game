package model;

//point class used to keep track of the snakes location
public class Point {
    private int locX, locY;

    public Point() {
        locX = 0;
        locY = 0;
    }

    public Point(int locX, int locY) {
        this.locX = locX;
        this.locY = locY;
    }

    public int getLocX() {
        return locX;
    }

    public void setLocX(int locX) {
        this.locX = locX;
    }

    public int getLocY() {
        return locY;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }
}
