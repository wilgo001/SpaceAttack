package com.iutdelaval.spaceattack.fire;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iutdelaval.spaceattack.MainActivity;
import com.iutdelaval.spaceattack.R;

import java.util.Timer;
import java.util.TimerTask;

public class Fire {

    private ImageView image;
    private final MainActivity context;
    private final float xPos;
    private float yPos;


    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public MainActivity getContext() {
        return context;
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

    public Fire(MainActivity context, float xPos, float yPos) {
        this.context = context;
        this.xPos = xPos;
        this.yPos = yPos;
        this.image = new ImageView(this.context);
        image.setBackgroundResource(R.drawable.firebase);
        image.setLayoutParams(new ViewGroup.LayoutParams(((int) context.maxWidth/10),((int) context.maxHeight/10)));
        context.relativeLayout.addView(image);
        image.setX(xPos);
        image.setY(yPos);
    }
}


