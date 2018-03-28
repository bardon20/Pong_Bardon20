package com.bardon20.pong_bardon20;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import java.util.Random;

/**
 * @author Bardon20 on 3/18/2018.
 */

public class MyAnimator implements Animator {
    int ballCountX=0;
    int ballCountY=0;
    int velX = 1;
    int velY = 2;
    float paddleLeft = 10;
    float paddleRight = paddleLeft+1200;
    boolean goBackwardsX = false;
    boolean goBackwardsY = false;
    boolean pause = false;
    boolean expertMode = false;
    int time = 30;
    RectF beginnerButton = new RectF(750F, 50F, 950F, 150F);
    RectF expertButton = new RectF(1000F, 50F, 1200F, 150F);

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
    public boolean doQuit() {
        return false;
    }


    /**
     * controls count, position and drawing of balls
     */
    @Override
    public void tick(Canvas g) {

        Random r = new Random();

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


        //changing count to position
        int numX = (ballCountX * 15) % 2025;
        int numY = (ballCountY * 15) % 1275;

        //1st, 2nd, and 3rd walls
        if (numX >= 2000 || numX <= 10) {
            goBackwardsX = !goBackwardsX;
        }
        if (numY <= 10) {
            goBackwardsY = !goBackwardsY;
        }
        //4th wall
        if (numY > 1250) {
            //choosing  new velocity for X and Y
            velX = r.nextInt(2)+1;
            velY = r.nextInt(2)+1;
            //creating new ball
            ballCountX = r.nextInt(1900) + 100;
            ballCountY = 0;
            //changing interval for velocity of ball randomly
            pause = true;
        }

        // "paddle" wall
        if (numX < paddleRight && numX > paddleLeft && numY ==1200) {
            goBackwardsY = !goBackwardsY;
        }

        if(!pause)
        {
            // Draw the ball in the correct position.
            Paint redPaint = new Paint();
            redPaint.setColor(Color.RED);
            g.drawCircle(numX, numY, 60, redPaint);
            redPaint.setColor(0xff0000ff);
        }


        // Draw the paddle
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        g.drawRect(paddleLeft, 1200, paddleRight, 1250, blackPaint);

        //draw beginnerButton, experButton and text
        Paint buttonPaint = new Paint();
        buttonPaint.setColor(Color.rgb(130, 130, 130));
        blackPaint.setTextSize(50F);
        g.drawRect(beginnerButton, buttonPaint);
        g.drawRect(expertButton, buttonPaint);
        g.drawText("Beginner", 750F, 100F, blackPaint);
        g.drawText(" Expert ", 1015F, 100F, blackPaint);

    }

    /**
     * "buttons" to change paddle size or
     * !pause game to add new ball after out of bounds
     */
    @Override
    public void onTouch(MotionEvent event) {

        //tracks clicks, create a new ball and buttons for expert vs. Beginner mode
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
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


    public void setPaddleWidth(int spread)
    {
        paddleRight = paddleLeft+spread;
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


