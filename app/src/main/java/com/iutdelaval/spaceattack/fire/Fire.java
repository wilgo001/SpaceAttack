package com.iutdelaval.spaceattack.fire;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iutdelaval.spaceattack.MainActivity;
import com.iutdelaval.spaceattack.R;
import com.iutdelaval.spaceattack.ennemis.Ennemi;

import java.util.Timer;
import java.util.TimerTask;

public class Fire {

    private ImageView image;
    private MainActivity context;
    private float xPos;
    private float yPos;
    private TimerTask movetask;
    private Timer movecount = new Timer();

    public Fire(final MainActivity context, final float xPos, float yPos) {
        this.context = context;
        this.xPos = xPos;
        this.yPos = yPos;
        this.image = new ImageView(context);
        this.image.setBackgroundResource(R.drawable.firebase);
        this.image.setLayoutParams(new ViewGroup.LayoutParams(context.fenetre.getWidth()/15, context.fenetre.getHeight()/15));
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
                        moveFire();
                    }
                });
            }
        };

        movecount.schedule(movetask, 1, 10);
    }

    private void moveFire() {
        this.image.setY(this.image.getY()-10);
        boolean destructionY;
        boolean destructionX;
        for(Ennemi ennemi : context.ennemiList) {
            destructionY = this.image.getY() >= ennemi.getyPos();
            destructionY = destructionY && (this.image.getY() <= ennemi.getyPos()+ ennemi.getSize());
            destructionX = this.image.getX() >= ennemi.getyPos();
            destructionX = destructionX && (this.image.getX() <= ennemi.getxPos()+ennemi.getSize());
            if (destructionX && destructionY) {
                this.remove();
                movecount.cancel();
                ennemi.loseLife();
                Log.v("debug", "ennemi : " + context.ennemiList.indexOf(ennemi) + "touché");
            }
        }

    }

    public ImageView getImage() {
        return image;
    }

    public void remove() {
        this.image.setY(0);
        this.image.setX(0);
        context.fenetre.removeView(image);
    }
}
