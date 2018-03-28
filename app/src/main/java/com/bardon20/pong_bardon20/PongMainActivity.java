package com.bardon20.pong_bardon20;

import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;



/**
 * PongMainActivity
 *
 * This is the activity for the Pong game. It attaches a PongAnimator to
 * an AnimationSurface.
 *
 * @author Andrew Nuxoll
 * @author Steven R. Vegdahl
 *
 */

/**
 * @author Bardon20
 *
 * @version March 2018.
 *
 *
 */

public class PongMainActivity extends AppCompatActivity {

    /**
     * creates an AnimationSurface containing a MyAnimator.
     */


    /**
     * [5%] When a ball leaves the field of play, don't add a new ball until the user indicates she is ready by
     * tapping the screen or a button for that purpose. Used button
     *
     *
     * [5%] Allow the user to change the size of the paddle (for “beginner” vs. “expert” mode) in some
     * manner. Used radio buttons
     */


    private AnimationSurface mySurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pong_main);

        // Connect the animation surface with the animator
        mySurface = (AnimationSurface) this.findViewById(R.id.animationSurface);
        mySurface.setAnimator(new MyAnimator());


    }

}


