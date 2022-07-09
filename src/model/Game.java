package model;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends Applet implements Runnable, KeyListener {

    private final int APPLET_WIDTH = 400, APPLET_HEIGHT = 400;
    boolean gameOver;
    private Snake snake;
    private Graphics tempGraphics;
    private Image tempImage;
    private Thread thread;
    private Token token;

    //initialize method used to start the applet etc
    public void init() {
        this.resize(APPLET_WIDTH, APPLET_HEIGHT);
        gameOver = false;
        tempImage = createImage(APPLET_WIDTH, APPLET_HEIGHT);
        tempGraphics = tempImage.getGraphics();
        this.addKeyListener(this);
        snake = new Snake();
        token = new Token(snake);
        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g) {
        //local variable for the origin of the window
        int origin = 0;

        //set the windows background to black
        tempGraphics.setColor(Color.BLACK);
        //fills the window with origin as well as the APPLET constants
        tempGraphics.fillRect(origin, origin, APPLET_WIDTH, APPLET_HEIGHT);

        //if the game isn't over then a snake and token is drawn to tempGraphics
        if (!gameOver) {
            snake.drawSnake(tempGraphics);
            token.drawToken(tempGraphics);
        }
        //else the game is over, and two strings are drawn to tell the player they lost and their score.
        else {
            tempGraphics.setColor(Color.RED);
            tempGraphics.drawString("Game Over!", 175, 200);
            tempGraphics.drawString("You collected " + token.getTokenScore() + " tokens", 150, 250);
        }

        g.drawImage(tempImage, origin, origin, null);
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void repaint(Graphics g) {
        paint(g);
    }

    @Override
    public void run() {
        //infinite for loop to make snake keep running until gameOver is true
        boolean done = true;

        for (; ; ) {

            if (!gameOver) {
                snake.move();
                this.checkGameOver();
                token.checkSnakeTokenCollision();
            }
            //repaints the snake every movement
            this.repaint();
            try {
                //speed of the snake (smaller number means a faster snake)
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkGameOver() {
        //the generated window is actually 396, so if the snakeX or Y is bigger, that means its outside the boundaries
        if (snake.getSnakeHeadX() < 0 || snake.getSnakeHeadX() > 396) {
            gameOver = true;
        }
        if (snake.getSnakeHeadY() < 0 || snake.getSnakeHeadY() > 396) {
            gameOver = true;
        }
        //calls upon snakeCollide to check if the snake collided with itself, if so then gameOver=true
        if (snake.snakeCollide()) {
            gameOver = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (!snake.isMoving()) {
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_RIGHT ||
                    e.getKeyCode() == KeyEvent.VK_DOWN) {
                snake.setIsMoving(true);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            //if snake isn't facing down, then it will go up
            if (snake.getyDir() != 1) {
                snake.setyDir(-1);
                snake.setxDir(0);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //if the snake isn't facing down, then it will go down
            if (snake.getyDir() != -1) {
                snake.setyDir(1);
                snake.setxDir(0);
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //if the snake isn't facing left, then it will go turn left
            if (snake.getxDir() != 1) {
                snake.setxDir(-1);
                snake.setyDir(0);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //if the snake isn't facing right, then it will go right
            if (snake.getxDir() != -1) {
                snake.setxDir(1);
                snake.setyDir(0);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
