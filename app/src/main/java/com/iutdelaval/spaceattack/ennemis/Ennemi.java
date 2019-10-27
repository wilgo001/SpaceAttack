package com.iutdelaval.spaceattack.ennemis;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.iutdelaval.spaceattack.R;

import java.util.Timer;
import java.util.TimerTask;

public class Ennemi {


    private ImageView image;
    private Context context;
    private final float xPos;
    private float yPos;
    private ConstraintLayout layout;



    public Ennemi(Context context, float xPos, float yPos, ConstraintLayout layout) {
        this.context = context;
        Log.println(Log.INFO, "debug", "context défini");
        this.layout = layout;
        Log.println(Log.INFO, "debug", "layout défini");
        this.xPos = xPos;
        Log.println(Log.INFO, "debug", "x défini");
        this.yPos = yPos;
        Log.println(Log.INFO, "debug", "y défini");
        this.image = new ImageView(this.context);
        Log.println(Log.INFO, "debug", "image défini");
        image.setBackgroundResource(R.drawable.othershipx3);
        image.setLayoutParams(new ViewGroup.LayoutParams(128, 128));
        this.layout.addView(image);
        Log.println(Log.INFO, "debug", "ajout layout défini");
        image.setX(xPos);
        Log.println(Log.INFO, "debug", "coX défini");
        image.setY(yPos);
        Log.println(Log.INFO, "debug", "coY crée");
        move();
    }

    private void move() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                image.setY(yPos+1);
                yPos = image.getY();
            }
        };
        timer.schedule(task, 10);
    }
}
