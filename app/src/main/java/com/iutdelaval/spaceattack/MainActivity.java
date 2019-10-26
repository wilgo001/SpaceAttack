package com.iutdelaval.spaceattack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageButton spaceshipImage;
    private Spaceship spaceship;
    private ConstraintLayout fenetre;
    public float maxWidth;
    public float maxHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fenetre =(ConstraintLayout) findViewById(R.id.fenetre);
        spaceshipImage = (ImageButton) findViewById(R.id.Spaceship);
    }

    @Override
    protected void onStart() {
        super.onStart();
        maxHeight = fenetre.getHeight();
        maxWidth = fenetre.getWidth();
        spaceship = new Spaceship(spaceshipImage, this, maxHeight, maxWidth);

    }
}
