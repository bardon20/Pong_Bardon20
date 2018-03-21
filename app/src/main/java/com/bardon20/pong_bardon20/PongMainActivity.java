package com.bardon20.pong_bardon20;

import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


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

    AnimationSurface mySurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pong_main);

        // Connect the animation surface with the animator
        mySurface = (AnimationSurface) this.findViewById(R.id.animationSurface);
        mySurface.setAnimator(new MyAnimator());

        Button addBallButton = (Button) this.findViewById(R.id.addButton);
        addBallButton.setOnClickListener(new addBallButtonListener());

        RadioGroup diffLevel = (RadioGroup)this.findViewById(R.id.difficultyLevel);
        diffLevel.setOnCheckedChangeListener(new radioButtonLister());

    }

    private class addBallButtonListener implements View.OnClickListener
    {
        public void onClick(View button) {

            mySurface.invalidate();
            MyAnimator newAnimator = new MyAnimator();
            newAnimator.setPause(false);
            mySurface.setAnimator(newAnimator);
        }
    }

    private class radioButtonLister implements RadioGroup.OnCheckedChangeListener
    {

        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            if( findViewById(checkedId) == findViewById(R.id.radioButtonBeginner))
            {
                mySurface.invalidate();
                MyAnimator newAnimator = new MyAnimator();
                newAnimator.setPaddleWidth(300);
                mySurface.setAnimator(newAnimator);

            }
            else if (findViewById(checkedId) == findViewById(R.id.radioButtonExpert))
            {

                mySurface.invalidate();
                MyAnimator newAnimator = new MyAnimator();
                newAnimator.setPaddleWidth(100);
                mySurface.setAnimator(newAnimator);
            }
        }
    }
}


