package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    //constants for the snakes size and starting position
    private final int SNAKE_WIDTH = 4;
    private final int START_SIZE = 20, STARTX = 150, STARTY = 150;

    //List to host the points of the snake
    private List<Point> snake;

    //boolean variables to tell the system whether the snake isMoving or if it should elongate if it collided with a token
    private boolean isMoving, extend;

    //if xDir is negative, then the snake is moving to the left, if yDir is negative, the snake is moving down etc
    private int xDir, yDir;


    public Snake() {
        //instantiating the snake with a new arraylist meaning its length is zero or whatever I set it to
        snake = new ArrayList<Point>();
        //the snake starts out in 0,0 and will head in the right direction
        xDir = 0;
        yDir = 0;

        //boolean variables are set false before the game starts
        isMoving = false;
        extend = false;
        snake.add(new Point(STARTX, STARTY));

        //creates the points for the snake
        for (int i = 1; i < START_SIZE; i++) {
            snake.add(new Point(STARTX - i * 4, STARTY));
        }
    }

    public void drawSnake(Graphics g) {
        //draws the snake based on the points in the list green
        g.setColor(Color.GREEN);
        for (Point p : snake) {
            g.fillRect(p.getLocX(), p.getLocY(), SNAKE_WIDTH, SNAKE_WIDTH);
        }
    }

    public boolean snakeCollide() {
        //temporary location variables used to test if the snake has moved into the same location as its other points
        int tempX = this.getSnakeHeadX();
        int tempY = this.getSnakeHeadY();

        //for loop used to check if the snake collided with itself. boolean is set to true if it did
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(i).getLocX() == tempX && snake.get(i).getLocY() == tempY) {
                return true;
            }
        }
        return false;
    }

    public void move() {
        if (isMoving) {
            //gets the first point of the snake
            Point tempPoint = snake.get(0);
            //gets the last point of the snake
            Point lastPoint = snake.get(snake.size() - 1);
            //calculates the new position of the snake
            Point newLocation = new Point(tempPoint.getLocX() + xDir * SNAKE_WIDTH, tempPoint.getLocY() + yDir * SNAKE_WIDTH);

            //iterates backwards through the snake. every new point becomes the previous point etc thus moving the snake
            for (int i = snake.size() - 1; i >= 1; i--) {
                //updates the specified index with the index before i
                snake.set(i, snake.get(i - 1));
            }
            snake.set(0, newLocation);

            /*calls on elongate, if its true, then that means the snake collided with a point and
            * it'll become longer and make elongate false until another point is captured
            * */
            if (extend) {
                snake.add(lastPoint);
                extend = false;
            }
        }
    }

    //boolean to check if the snake is moving
    public boolean isMoving() {
        return isMoving;
    }

    public void setIsMoving(boolean bool) {
        isMoving = bool;
    }

    //getters and setters for the location and direction of the snake
    public int getxDir() {
        return xDir;
    }

    public void setxDir(int xDir) {
        this.xDir = xDir;
    }

    public int getyDir() {
        return yDir;
    }

    public void setyDir(int yDir) {
        this.yDir = yDir;
    }

    public boolean isExtend() {
        return extend;
    }

    public void setExtend(boolean extend) {
        this.extend = extend;
    }

    //getters for the location of the snake head. This helps us find out if a snake bumped into a wall, or point etc
    public int getSnakeHeadX() {
        return snake.get(0).getLocX();
    }

    public int getSnakeHeadY() {
        return snake.get(0).getLocY();
    }
}
