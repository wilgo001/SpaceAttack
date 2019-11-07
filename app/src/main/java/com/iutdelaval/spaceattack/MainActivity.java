package com.iutdelaval.spaceattack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.iutdelaval.spaceattack.ennemis.Ennemi;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Spaceship spaceship;
    private ConstraintLayout fenetre;
    public RelativeLayout relativeLayout;
    public float maxWidth;
    public float maxHeight;
    public List<Ennemi> ennemis = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fenetre =(ConstraintLayout) findViewById(R.id.fenetre);
        relativeLayout = (RelativeLayout) findViewById(R.id.layout);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.println(Log.INFO, "debug", "on start lancé");
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        maxWidth = metrics.widthPixels;
        maxHeight = metrics.heightPixels;
        Log.println(Log.INFO, "debug", maxHeight+"/"+maxWidth);
        spaceship = new Spaceship(this);

        spaceship.getImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spaceship.setAccelerometre();
                createEnnemis();

            }
        });

    }

    private void createEnnemis() {

        Timer timerCreate = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        Log.println(Log.INFO, "debug", "nouvelle vague d'ennemi");
                        float xPos = 0;
                        float yPos = -50;
                        for (int i = 0; i < 5; i++) {
                            createEnnemi(xPos, yPos);
                            Log.println(Log.INFO, "debug", "nouvel ennemi crée");
                            xPos += maxWidth/5;
                        }

                    }
                });

            }
        };
        Timer timerMove = new Timer();
        TimerTask taskMove = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        for(Ennemi ennemi : ennemis) {
                            ennemi.move();
                        }
                    }
                });
            }
        };
        timerCreate.schedule(task, 1000, 2000);
        timerMove.schedule(taskMove, 1, 10);
    }
    public void createEnnemi(float xPos, float yPos) {
        Ennemi ennemi = new Ennemi(this, xPos, yPos);
        Log.println(Log.INFO, "debug", "création de l'ennemi");



        Log.println(Log.INFO, "debug", "ajout au layout");
        ennemis.add(ennemi);
        Log.println(Log.INFO, "debug", "ajout à la liste ennemis");
        ennemi.startTravel();
    }

    protected void onStop() {
        super.onStop();
        spaceship.destroy();
    }

    public void deleteEnnemi(Ennemi ennemi) {
        ennemis.remove(ennemi);
    }


}
