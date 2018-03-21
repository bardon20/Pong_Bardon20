package com.bardon20.pong_bardon20;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.Random;

/**
 * Created by rbard on 3/18/2018.
 */

public class MyAnimator implements Animator {
    int ballCountX=0;
    int ballCountY=0;
    int numBalls = 1;
    float paddleLeft = 40;
    float paddleRight = paddleLeft+300;
    boolean goBackwardsX = false;
    boolean goBackwardsY = false;
    boolean pause = false;
    int time = 30;

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

    @Override
    public void tick(Canvas g) {

        Random r = new Random();

        //increasing or decreasing x and y for position of ball
        if (goBackwardsX) {
            ballCountX--;
        } else {
            ballCountX++;
        }
        if (goBackwardsY) {
            ballCountY--;
        } else {
            ballCountY++;
        }

        int numX = (ballCountX * 15) % 1700;
        int numY = (ballCountY * 15) % 1275;

        //1st, 2nd, and 3rd walls
        if (numX > 1675 || numX < 10) {
            goBackwardsX = !goBackwardsX;
        }
        if (numY < 10) {
            goBackwardsY = !goBackwardsY;
        }
        //4th wall
        if (numY > 1250) {
            /*//creating new ball
            ballCountX = r.nextInt(1900) + 100;
            ballCountY = 0;
            //changing interval for velocity of ball randomly
            time = r.nextInt(60);
            numBalls++;*/
            pause = true;
        }

        // "paddle" wall
        if (numX < paddleRight && numX > paddleLeft && numY == 1200) {
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

    }


    @Override
    public void onTouch(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
        }

    }

    public void setPaddleWidth(int spread)
    {
        paddleRight = paddleLeft+spread;
    }

    public void setPause(boolean isPaused)
    {
        pause = isPaused;
    }

}
