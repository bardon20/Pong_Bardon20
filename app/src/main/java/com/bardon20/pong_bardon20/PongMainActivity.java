package com.bardon20.pong_bardon20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


/**
 * PongMainActivity
 *
 * This is the activity for the Pong game. It attaches a PongAnimator to
 * an AnimationSurface.
 *
 * @author Andrew Nuxoll
 * @author Steven R. Vegdahl
 * @version July 2013
 *
 */

public class PongMainActivity extends AppCompatActivity {

    /**
     * creates an AnimationSurface containing a TestAnimator.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pong_main);

        // Connect the animation surface with the animator
        AnimationSurface mySurface = (AnimationSurface) this.findViewById(R.id.animationSurface);
        mySurface.setAnimator(new MyAnimator());

        Button addBallButton = (Button) this.findViewById(R.id.addButton);
        addBallButton.setOnClickListener(new addBallButtonListener());







    }

    private class addBallButtonListener implements View.OnClickListener
    {
        public void onClick(View button) {
            new MyAnimator().setPause(false);

        }
    }


}


