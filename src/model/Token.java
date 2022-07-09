package model;

import java.awt.*;

public class Token {

    private int tokenX, tokenY, tokenScore;
    private int tokenSize = 5;
    private Snake snake;

    public Token(Snake s) {
        /*creates the first random position for the token.
         since the actual generated area of the window is less than 400, and the kill wall is 396,
          I set the maximum area to 395 just inside the border*/
        newPosition();
        snake = s;
    }

    public void newPosition() {
        //method called on when to randomize the next location of the point
        tokenX = (int) (Math.random() * 395);
        tokenY = (int) (Math.random() * 395);
    }

    //draws a token
    public void drawToken(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(tokenX, tokenY, tokenSize, tokenSize);
    }

    public boolean checkSnakeTokenCollision() {
        //gets the exact center of the snakes head instead of the first pixel.
        int snakeX = snake.getSnakeHeadX() + 2;
        int snakeY = snake.getSnakeHeadY() + 2;

        /*if even one pixel of the snakes head collides with a pixel of the points location,
        it counts as a collision and token score gets incremented and a new position is called,
        else the boolean is just false and nothing happens
        * */
        if (snakeX >= tokenX - 1 && snakeX <= (tokenX + 7)) {
            if (snakeY >= tokenY - 1 && snakeY <= (tokenY + 7)) {
                snake.setExtend(true);
                newPosition();
                tokenScore++;
                return true;
            }
        }
        return false;
    }

    //getter used to retrieve amount of tokens a player has retrieved
    public int getTokenScore() {
        return tokenScore;
    }
}
