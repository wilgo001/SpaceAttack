package com.iutdelaval.spaceattack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
        maxHeight = fenetre.getHeight();
        maxWidth = fenetre.getWidth();
        spaceship = new Spaceship(this, maxHeight, maxWidth, fenetre);

    }

    protected void onPause() {
        super.onPause();
        spaceship.destroy();
    }
}
