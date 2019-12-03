package com.iutdelaval.spaceattack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.iutdelaval.spaceattack.ennemis.Ennemi;
import com.iutdelaval.spaceattack.fire.Fire;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public Spaceship spaceship;
    public RelativeLayout fenetre;
    boolean spaceshipCreated = true;
    public List<Ennemi> ennemiList = new ArrayList<>();
    private int level;
    private int nbVague;
    private int nbLife;
    public boolean pause = false;
    private Pause interfacePause;
    public ImageButton imageButton;
    public MediaPlayer introMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fenetre = (RelativeLayout) findViewById(R.id.fenetre);
        spaceship = new Spaceship(this);
        Intent intent = this.getIntent();
        level = intent.getIntExtra(ListActivity.SPACEATTACK_LEVEL, 0);
        nbVague = level * 20;
        nbLife = level * 2;
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        introMusic = MediaPlayer.create(this.getApplicationContext(), R.raw.music);
        introMusic.setLooping(true);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //createStars();
        if(spaceshipCreated) {
            spaceship.start();
            spaceshipCreated = false;
            createSpaceship();
        }
    }

    private void createSpaceship() {
        for (int n = 1; n <= nbVague; n++) {
            for (int i=0; i < 5; i++) {
                Ennemi ennemi = new Ennemi(MainActivity.this, fenetre.getWidth()*i/6 + fenetre.getWidth()*i/24, -(fenetre.getHeight()/5)*n);
                ennemiList.add(ennemi);
            }
        }
        imageButton.bringToFront();
    }


    public void setLose() {
        setPause(Pause.PERDU);
    }
    public void setPause(final String text) {
        pause = true;
        for (Fire fire : spaceship.fireList) {
            this.fenetre.removeView(fire.getImage());
        }
        interfacePause = new Pause(MainActivity.this, text);

    }

    public void buttonPause(View v) {
        setPause(Pause.PAUSE);
    }

    public void resume() {
        pause = false;
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

}
