package com.smart.moneymanager.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.smart.moneymanager.DataController.MySharedPreferences;
import com.smart.moneymanager.R;

public class ActivitySplash extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1500;
    private LinearLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        playAnimation();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                MySharedPreferences msp = new MySharedPreferences(getApplicationContext());
                    Intent mainIntent = new Intent(getApplicationContext(), ActivityStart.class);
                    startActivity(mainIntent);

                ActivitySplash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    private void playAnimation(){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        mainLayout.startAnimation(animation);
    }
}
