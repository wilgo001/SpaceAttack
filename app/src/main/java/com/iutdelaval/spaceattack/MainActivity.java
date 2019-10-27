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
    public float maxWidth;
    public float maxHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fenetre =(ConstraintLayout) findViewById(R.id.fenetre);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.println(Log.INFO, "debug", "on start lancé");
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        maxWidth = metrics.widthPixels;
        maxHeight = metrics.heightPixels;
        Log.println(Log.INFO, "debug", maxHeight+"/"+maxWidth);
        spaceship = new Spaceship(this, maxHeight, maxWidth, fenetre);
    }

    protected void onStop() {
        super.onStop();
        spaceship.destroy();
    }
}
