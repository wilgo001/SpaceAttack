package com.iutdelaval.spaceattack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
    private RelativeLayout relativeLayout;
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
        spaceship = new Spaceship(this, maxHeight, maxWidth, fenetre, relativeLayout);

        //createEnnemis();

    }

    private void createEnnemis() {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.println(Log.INFO, "debug", "nouvelle vague d'ennemi");
                float xPos = 0;
                float yPos = 50;
                for (int i = 0; i < 5; i++) {
                    Log.println(Log.INFO, "debug", "nouvel ennemi");
                    createEnnemi(xPos, yPos);
                    Log.println(Log.INFO, "debug", "nouvel ennemi crée");
                    xPos += maxWidth/5;
                }
            }
        };
        timer.schedule(task, 0, 2000);
    }
    public void createEnnemi(float xPos, float yPos) {

    }

    protected void onStop() {
        super.onStop();
        spaceship.destroy();
    }
}
