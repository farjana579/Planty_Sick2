package com.example.plantysick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Splash_Screen extends AppCompatActivity {

    //variable for splash text view


    TextView splashText;
    private static int SPLASH_SCREEN = 2000;
    Animation bottomAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //find the anim resource file
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        //setting animation time
         bottomAnim.setDuration(1000);

         //find the widget of splash screen by id
        splashText = findViewById(R.id.splash_text);

        //setting animation element
        splashText.setAnimation(bottomAnim);
        //after animation end
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);


    }
}