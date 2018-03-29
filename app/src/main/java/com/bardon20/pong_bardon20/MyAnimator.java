package com.bardon20.pong_bardon20;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import java.util.Random;

/**
 *
 *class that animates a ball bouncing off walls and paddle at random velocity and angle
 * animated paddle based on user touch
 *
 *
 * Part 1 features:
 *
 * [5%] When a ball leaves the field of play, don't add a new ball until the user indicates she is ready by
 * tapping the screen or a button for that purpose. Used button
 *
 *
 * [5%] Allow the user to change the size of the paddle (for “beginner” vs. “expert” mode) in some
 * manner. Used radio buttons
 *
 *
 * Part 2 features:
 *
 * [5%] Incorporate a certain amount of randomness in the bouncing. For example, the angle of
 * deflection might be slightly different than "perfect," or the speed of bounce-back might be greater or
 * less than the speed at wall-contact.
 *
 * [5%] Keep a running score, which the user can see. For example the user might gain a point each time
 * the ball is batted back but lose 5 points each time the ball escapes.
 *
 *
 * [5%] Have the game end when the user loses a certain number of balls. The balls remaining should be
 * displayed where the user can see.
 *
 *
 * @author Bardon20 on 3/18/2018.
 */

public class MyAnimator implements Animator {
    int ballCountX=0;
    int ballCountY=0;
    int velX = 1;
    int velY = 2;
    int score;
    int ballsLost=0;
    float paddleLeft = 10;
    float paddleRight = paddleLeft+300;
    boolean goBackwardsX = false;
    boolean goBackwardsY = false;
    boolean pause = false;
    boolean quit = false;
    boolean expertMode = false;
    int time = 30;
    RectF beginnerButton = new RectF(750F, 50F, 950F, 150F);
    RectF expertButton = new RectF(1000F, 50F, 1200F, 150F);
    Random r = new Random();

    @Override
    public int interval() {
        return time;
    }

    @Override
    public int backgroundColor() {
        return Color.rgb(180, 200, 255);
    }

    @Override
    public boolean doPause() {
        return pause;
    }

    @Override
    public boolean doQuit() {return quit;}


    /**
     * controls count, position and drawing of balls
     */
    @Override
    public void tick(Canvas g) {

        ballPosition();

        //changing count to position
        int numX = (ballCountX * 15) % 2025;
        int numY = (ballCountY * 15) % 1275;

        setWalls(numX, numY);
        drawBall(numX, numY, g);
        drawPaddle(g);
        drawScoreLostBalls(g);

    }

    public void ballPosition()
    {
        //increasing or decreasing x and y for position of ball
        if (goBackwardsX) {
            ballCountX = ballCountX-velX;
            //ballCountX--;
        }
        else {
            ballCountX = ballCountX+velX;
            //ballCountX++;
        }
        if (goBackwardsY) {;
            ballCountY = ballCountY-velY;
            //ballCountY--;
        }
        else {
            ballCountY = ballCountY+velY;
            //ballCountY++;
        }
    }

    public void setWalls(int numX, int numY)
    {
        //1st, 2nd, and 3rd walls
        if (numX >= 2000 || numX <= 10) {
            goBackwardsX = !goBackwardsX;
            randomBounce();
        }
        if (numY <= 10) {
            goBackwardsY = !goBackwardsY;
            randomBounce();
        }
        //4th wall
        if (numY > 1250) {
            randomBounce();
            //creating new ball
            ballCountX = r.nextInt(1900) + 100;
            ballCountY = 0;
            //changing interval for velocity of ball randomly
            score = score-5;
            ballsLost++;
        }

        // "paddle" wall
        if (numX < paddleRight && numX > paddleLeft && numY ==1200) {
            goBackwardsY = !goBackwardsY;
            //keep track of bounces off of paddle for score
            score++;
            randomBounce();
        }
    }

    //draws the score on the AnimationSurface
    public void drawScoreLostBalls(Canvas g)
    {
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setTextSize(50F);
        g.drawText("Score: "+score, 750F, 100F, blackPaint);
        g.drawText("Lost Balls:"+ballsLost, 1015F, 100F, blackPaint);
        if(ballsLost==6)
        {
            //displays message and ends game if too many balls lost
            g.drawText("Game Over", 900F, 600F, blackPaint);
            quit = true;
        }
    }

    //creates an random velocity when called
    public void randomBounce()
    {
        //choosing  new velocity for X and Y
        velX = r.nextInt(2)+1;
        velY = r.nextInt(2)+1;
    }

    public void drawBall(int numX, int numY, Canvas g)
    {
        // Draw the ball in the correct position.
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        g.drawCircle(numX, numY, 60, redPaint);
        redPaint.setColor(0xff0000ff);
    }

    public void drawPaddle (Canvas g)
    {
        // Draw the paddle
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        g.drawRect(paddleLeft, 1200, paddleRight, 1250, blackPaint);
    }

    //draws buttons to change paddle size when called
    public void drawButtons(Canvas g)
    {
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);

        //draw beginnerButton, experButton and text
        Paint buttonPaint = new Paint();
        buttonPaint.setColor(Color.rgb(130, 130, 130));
        blackPaint.setTextSize(50F);
        g.drawRect(beginnerButton, buttonPaint);
        g.drawRect(expertButton, buttonPaint);
        g.drawText("Beginner", 750F, 100F, blackPaint);
        g.drawText(" Expert ", 1015F, 100F, blackPaint);
    }

    //changes the paddle width to redraw the paddle
    public void setPaddleWidth(int spread)
    {
        paddleRight = paddleLeft+spread;
    }

    //changes paddle width and unpauses the game based on clicks
    public void pongPartOneCheck(MotionEvent event)
    {

        //tracks clicks, create a new ball and buttons for expert vs. Beginner mode
        if(event.getX()<950 && event.getX()>750 && event.getY()<150 && event.getY()>50 )

        {
            setPaddleWidth(300);
        }
        else if(event.getX()<1200 && event.getX()>100 && event.getY()<150 && event.getY()>50)
        {
            setPaddleWidth(100);
        }
        else
        {
            pause = false;
        }
    }

    /**
     * "buttons" to change paddle size or
     * !pause game to add new ball after out of bounds
     */
    @Override
    public void onTouch(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {

        }
        //Paddle movement, follows the users touch
        if (event.getY() > 1000)
        {
            paddleLeft = event.getX();
            if (expertMode)
            {
                setPaddleWidth(100);
            }
            else
            {
                setPaddleWidth(300);
            }
        }
    }
}




/**
 External Citation
 Date: 3-21-2018
 Problem: getting x and y location of click
 Resource:
 http:https://stackoverflow.com/questions/1967039/onclicklistener-x-y-location-of-event
 Solution: I used the example code from this post.
 */
/**
 External Citation
 Date: 3-21-2018
 Problem: grawign text on surface view
 Resource:https://developer.android.com/reference/android/graphics/Canvas.html
 Solution: looked it up
 */
/**
 External Citation
 Date: 3-21-2018
 Problem: getting the paddle to follow the users finger
 Resource:https://developer.android.com/reference/android/view/MotionEvent.html
 Solution: looked it up, ended up using none of the ACTION_...
 */


