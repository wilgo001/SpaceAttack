package com.iutdelaval.spaceattack;

import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Stars {

    private ImageView image;
    private MainActivity context;
    private float xPos;
    private float yPos;
    private TimerTask movetask;
    private Timer movecount = new Timer();

    public Stars(final MainActivity context, final float xPos, float yPos) {
        this.context = context;
        this.xPos = xPos;
        this.yPos = yPos;
        this.image = new ImageView(context);
        this.image.setBackgroundResource(R.drawable.background);
        this.image.setLayoutParams(new ViewGroup.LayoutParams(context.fenetre.getWidth(), context.fenetre.getHeight()));
        this.image.setX(xPos);
        this.image.setY(yPos);
        this.context.fenetre.addView(image);
        move();
    }

    private void move() {
        movetask = new TimerTask() {
            @Override
            public void run() {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        moveStar();
                    }
                });
            }
        };

        movecount.schedule(movetask, 1, 500);
    }

    private void moveStar() {
        this.image.setY(this.image.getY()-7);
    }
}
