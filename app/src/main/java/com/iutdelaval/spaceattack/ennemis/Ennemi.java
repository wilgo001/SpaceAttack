package com.iutdelaval.spaceattack.ennemis;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.iutdelaval.spaceattack.R;

import java.util.Timer;
import java.util.TimerTask;

public class Ennemi {


    private ImageView image;
    private Context context;
    private final float xPos;
    private float yPos;
    private RelativeLayout layout;



    public Ennemi(Context context, float xPos, float yPos, RelativeLayout layout) {
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
        Log.println(Log.INFO, "debug", "ajout background image");
        image.setLayoutParams(new ViewGroup.LayoutParams(128, 128));
        Log.println(Log.INFO, "debug", "ajout taille image");
        this.layout.addView(image);
        Log.println(Log.INFO, "debug", "ajout layout défini");
        image.setX(xPos);
        Log.println(Log.INFO, "debug", "coX défini");
        image.setY(yPos);
        Log.println(Log.INFO, "debug", "coY crée");

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                move();
            }
        };
        timer.schedule(task, 1, 10);
    }

    private void move() {
        yPos++;
        image.setY(yPos);

    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public ImageView getImage() {
        return image;
    }
}
