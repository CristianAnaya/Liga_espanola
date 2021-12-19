package com.cranaya.ligaespaola.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.cranaya.ligaespaola.R;
import com.cranaya.ligaespaola.connection.NetworkUtilities;

import static com.cranaya.ligaespaola.connection.NetworkUtilities.getTeam;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = NetworkUtilities.class.getSimpleName();

    private Animation animation;
    private LinearLayout splashDegrado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splashDegrado = findViewById(R.id.splashDegrado);
        animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash);
        splashDegrado.startAnimation(animation);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(SplashActivity.this,EquiposActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        getTeam();
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
    }
}