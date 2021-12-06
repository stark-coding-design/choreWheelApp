package com.stark.satos.chorewheelapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private static final String [] sectors = {"To Dust The Picture Frames", " To Put The Away Groceries", "To Pick Your Own Chore From The List",
            "To Put Away The Dry Dishes", "To Sweep The Porch", "To Wash Some Clothes", "To Set And Then Clear The Dinner Table",
            "To Take Care Of The Animals", "To Dust The Furniture", "To Take A Little Break", "To Take Out The Trash",
            "To Have Your Parent Choose Your Next Chore", "To Clean The Windows", " To Clean The Bathrooms", "To Fold Clothes",
            "To Sweep The House", "To Wash The Dishes", "To Mop The Floors", "To Vacuum", "To Help Wth Dinner",
            "To Yard Work", "To Clean Family Room", "To Have Your Parent Choose Your Next Chore", "To Clean The Kitchen"};
    private static final int [] sectorDegrees = new int[sectors.length];
    private static final Random random = new Random();
    private int degree = 0;
    private boolean isSpinning = false;

    ImageView wheel;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.constraint_layout);
        wheel = findViewById(R.id.spinningWheel);


        layout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                if(!isSpinning){
                    spin();
                    isSpinning = true;
                }
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                if(!isSpinning){
                    spin();
                    isSpinning = true;
                }
            }
        });
        getDegreeeForSectors();

    }

    private void getDegreeeForSectors() {
        int sectorDegree = 360/ sectors.length;

        for(int i = 0; i < sectors.length; i++){
            sectorDegrees[i] = (i + 1) * sectorDegree;
        }
    }

    private void spin(){
        degree = random.nextInt(sectors.length - 1);

        RotateAnimation rotateAnimation = new RotateAnimation(0, (360 * sectors.length) + sectorDegrees[degree],
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setDuration(3600);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(MainActivity.this, "You Get " + sectors[sectors.length - (degree + 1)] + "!!", Toast.LENGTH_LONG).show();
                isSpinning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        wheel.startAnimation(rotateAnimation);
    }
}