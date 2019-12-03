package com.iutdelaval.spaceattack.ennemis;

import android.graphics.Color;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.iutdelaval.spaceattack.MainActivity;
import com.iutdelaval.spaceattack.R;
import com.iutdelaval.spaceattack.fire.Fire;

import android.os.Handler;

public class Ennemi {

    private MainActivity context;
    private ImageView image;
    private Handler handler;
    private int life = 5;

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
                if (!context.pause)
                    moveEnnemi();
                if (image.getY()>=context.fenetre.getHeight()) {
                    remove();
                    life = 0;
                }
                handler.postDelayed(this, 50);
            }
        };
        handler.postDelayed(run, 0);
    }

    private void moveEnnemi() {
        this.image.setY(this.image.getY()+5);
        boolean destructionY;
        boolean destructionX;
        destructionX = (this.image.getX() >= context.spaceship.getImage().getX());
        destructionX = destructionX && (this.image.getX() + this.image.getWidth() <= context.spaceship.getImage().getX());
        destructionY = (this.image.getY() >= context.spaceship.getImage().getY());
        destructionY = destructionY && (this.image.getY() + this.image.getHeight() <= context.spaceship.getImage().getY());
        if (destructionX && destructionY) {
            context.setLose();
        }

    }


    public void remove() {
        this.image.setLayoutParams(new RelativeLayout.LayoutParams(0, 0));
        this.image.setX(0f);
        this.image.setY(0f);
        context.fenetre.removeView(image);
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
        life--;
        image.setBackgroundResource(R.drawable.ennemislane);
        Runnable run = new Runnable() {
            @Override
            public void run() {
                image.setBackgroundResource(R.drawable.othershipx);
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(run, 500);
        if (life < 0) {
            remove();
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
