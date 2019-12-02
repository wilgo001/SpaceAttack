package com.iutdelaval.spaceattack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.iutdelaval.spaceattack.ennemis.Ennemi;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public Spaceship spaceship;
    public RelativeLayout fenetre;
    boolean spaceshipCreated = true;
    public List<Ennemi> ennemiList = new ArrayList<>();
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fenetre = (RelativeLayout) findViewById(R.id.fenetre);
        spaceship = new Spaceship(this);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //createStars();
        if(spaceshipCreated) {
            spaceship.start();
            spaceshipCreated = false;
            Timer ennemiCount = new Timer();
            TimerTask ennemiTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            createSpaceship();

                        }
                    });
                }
            };
            ennemiCount.schedule(ennemiTask, 1000, 4000);
            //createSpaceship();
        }
    }

    private void createStars() {
        handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                createStar();
                handler.postDelayed(this, 500);
            }
        };
        handler.postDelayed(run, 0);
        Timer starCount = new Timer();
        TimerTask starTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        createStar();

                    }
                });
            }
        };
        //starCount.schedule(starTask, 0, 500);
    }

    private void createStar() {
            Stars star = new Stars(MainActivity.this, (float) (fenetre.getWidth()*Math.random()), -200f);
    }

    private void createSpaceship() {
        for (Ennemi ennemi : ennemiList) {
            if (ennemi.getLife() <= 0)
                fenetre.removeView(ennemi.getImage());
                ennemiList.remove(ennemi);
        }
        for (int i=0; i < 5; i++) {
            Ennemi ennemi = new Ennemi(MainActivity.this, fenetre.getWidth()*i/6 + fenetre.getWidth()*i/24, -200f);
            ennemiList.add(ennemi);
        }
    }
}
