package com.iutdelaval.spaceattack.ennemis;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.iutdelaval.spaceattack.MainActivity;
import com.iutdelaval.spaceattack.R;

public class Ennemi {


    private ImageView image;
    private MainActivity context;
    private final float xPos;
    private float yPos;
    private RelativeLayout layout;



    public Ennemi(MainActivity context, float xPos, float yPos) {
        this.context = context;
        //Log.println(Log.INFO, "debug", "context défini");
        this.layout = layout;
        this.xPos = xPos;
        this.yPos = yPos;
        this.image = new ImageView(this.context);
        image.setBackgroundResource(R.drawable.spaceship);
        image.setLayoutParams(new ViewGroup.LayoutParams(128, 128));
        image.setX(xPos);
        image.setY(yPos);
        context.relativeLayout.addView(image);
    }

    public void move() {
        yPos++;
        if(yPos>context.maxWidth)
            context.deleteEnnemi(this);
        image.setY(yPos);

    }

    public void startTravel() {
        image.setX(xPos);
        //Log.println(Log.INFO, "debug", "coX défini");
        image.setY(yPos);
        //Log.println(Log.INFO, "debug", "coY crée");
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
