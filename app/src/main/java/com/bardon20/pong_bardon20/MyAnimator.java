package com.bardon20.pong_bardon20;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

/**
 * Created by rbard on 3/18/2018.
 */

public class MyAnimator implements Animator {
    int countX=0;
    int countY=0;
    boolean goBackwards = false;

    @Override
    public int interval() {
        return 30;
    }

    @Override
    public int backgroundColor() {
        return Color.rgb(180, 200, 255);
    }

    public void goBackwards(boolean b) {
        // set our instance variable
        goBackwards = b;
    }


    @Override
    public boolean doPause() {
        return false;
    }

    @Override
    public boolean doQuit() {
        return false;
    }

    @Override
    public void tick(Canvas g) {
        if(goBackwards)
        {
            countX--;
            countY--;
        }
        else
        {
            countX++;
            countY++;
        }

        int numX = (countX*15)%1975;
        int numY = (countY*15)%1225;

        /*if(X<0 || countY<0 || countX> || countY>600)
        {
            goBackwards = !goBackwards;
        }*/


    }

    @Override
    public void onTouch(MotionEvent event) {

    }
}
