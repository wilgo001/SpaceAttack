package com.iutdelaval.spaceattack.ennemis;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.iutdelaval.spaceattack.MainActivity;
import com.iutdelaval.spaceattack.R;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

public class Ennemi {

    private MainActivity context;
    private ImageView image;
    private TimerTask movetask;
    private Timer movecount;
    private Handler handler;
    private int life = 1000;

    public Ennemi(MainActivity context, float xPos, float yPos) {
        this.context = context;
        this.image = new ImageView(context);
        this.handler = new Handler();
        this.image.setBackgroundResource(R.drawable.othershipx);
        this.image.setLayoutParams(new RelativeLayout.LayoutParams(context.fenetre.getWidth()/6, context.fenetre.getWidth()/6));
        this.image.setX(xPos);
        this.image.setY(yPos);
        this.context.fenetre.addView(this.image);
        move();
    }

    private void move() {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                moveEnnemi();
                if (image.getY()>=context.fenetre.getHeight()) {
                    remove();
                    life = 0;
                }else {
                    handler.postDelayed(this, 50);
                }
            }
        };
        handler.postDelayed(run, 0);
    }

    private void moveEnnemi() {
        this.image.setY(this.image.getY()+5);
    }


    public void remove() {
        context.fenetre.removeView(image);
        movecount.cancel();
    }

    public float getxPos() {
        return image.getX();
    }

    public float getyPos() {
        return image.getY();
    }

    public int getSize() {
        return this.image.getWidth();
    }

    public void loseLife() {
        if (life < 0) {
            //remove();
        }else {
            life--;
            //image.setBackgroundResource(R.color.white);
        }

    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public View getImage() {
        return image;
    }
}
