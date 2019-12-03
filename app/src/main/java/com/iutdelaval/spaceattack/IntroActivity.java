package com.iutdelaval.spaceattack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import android.animation.Animator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class IntroActivity extends AppCompatActivity {

    public MediaPlayer introMusic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        introMusic = MediaPlayer.create(this.getApplicationContext(), R.raw.music);
        introMusic.setLooping(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        introMusic.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        introMusic.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        introMusic.setVolume(0.5f, 0.5f);
        introMusic.start();
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }




}
