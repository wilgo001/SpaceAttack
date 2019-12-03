package com.iutdelaval.spaceattack.fire;

import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iutdelaval.spaceattack.MainActivity;
import com.iutdelaval.spaceattack.R;
import com.iutdelaval.spaceattack.ennemis.Ennemi;

public class Fire {

    private ImageView image;
    private MainActivity context;
    private float xPos;
    private float yPos;
    private boolean stop = false;
    private Runnable movetask;
    private Handler movecount = new Handler();

    public Fire(final MainActivity context, final float xPos, float yPos) {
        this.context = context;
        this.xPos = xPos;
        this.yPos = yPos;
        this.image = new ImageView(context);
        this.image.setBackgroundResource(R.drawable.firebase);
        this.image.setLayoutParams(new ViewGroup.LayoutParams(context.fenetre.getWidth()/15, context.fenetre.getHeight()/15));
        this.image.setX(xPos-context.fenetre.getWidth()/30);
        this.image.setY(yPos);
        this.context.fenetre.addView(image);
        move();
    }

    private void move() {
        movetask = new Runnable() {
            @Override
            public void run() {
                if (!context.pause)
                    moveFire();
                if (!stop && !context.pause) {
                    movecount.postDelayed(this, 10);
                }
            }
        };

        movecount.postDelayed(movetask, 1);
    }

    private void moveFire() {
        this.image.setY(this.image.getY()-10);
        boolean destructionY;
        boolean destructionX;
        for(Ennemi ennemi : context.ennemiList) {
            destructionY = this.image.getY() >= ennemi.getyPos();
            destructionY = destructionY && (this.image.getY() <= ennemi.getyPos()+ ennemi.getSize());
            destructionX = this.image.getX() >= ennemi.getxPos();
            destructionX = destructionX && (this.image.getX() <= ennemi.getxPos()+ennemi.getSize());
            if (destructionX && destructionY && (ennemi.getyPos()+ennemi.getSize())>=0) {
                this.remove();
                ennemi.loseLife();
            }
        }

    }

    public ImageView getImage() {
        return image;
    }

    public void remove() {
        stop = true;
        context.fenetre.removeView(image);
    }

    public float getyPos() {
        return image.getY();
    }

    public float getWidth() {
        return image.getWidth();
    }

    public float getHeight() {
        return image.getHeight();
    }

    public float getxPos() {
        return image.getX();
    }
}
