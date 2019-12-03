package com.iutdelaval.spaceattack.ennemis;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.iutdelaval.spaceattack.MainActivity;
import com.iutdelaval.spaceattack.fire.Laser;

public class Boss extends Ennemi {

    private ImageView image;
    private MainActivity context;
    private int life;
    private Handler handler = new Handler();
    private int step;
    private float posXlaser;
    private Laser laser;

    public Boss(MainActivity context, float xPos, float yPos, int size, int life) {
        super(context, xPos, yPos, size, life*5);
        this.image = super.image;
        this.context = super.context;
        this.life = super.life;
        this.laser = new Laser(context, 0, 0);
    }

    public void move() {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                if (!context.pause)
                    moveEnnemi();
                if (!(image.getY()>=-image.getWidth()/2)) {
                    handler.postDelayed(this, 50);
                }else {
                    attaque();
                }
            }
        };
        handler.postDelayed(run, 0);
    }

    @Override
    public void remove() {
        super.remove();
        context.setWin();
    }

    public void attaque() {
        step = 0;
        Runnable run = new Runnable() {
            @Override
            public void run() {
                if (!context.pause) {
                    switch (step) {
                        case 0:
                            posXlaser = context.spaceship.getImage().getX();
                            laser.setxPos((int) posXlaser);
                            laser.alert();
                            step = 1;
                            break;
                        case 1:
                            laser.laser();
                            step = 2;
                            break;
                        case 2:
                            laser.stop();
                            step = 3;
                            break;
                        case 3:
                            //just for wait
                            step = 0;
                            break;
                        default:
                            step = 0;
                            break;
                    }
                }
                handler.postDelayed(this, 4000);
            }
        };
        handler.postDelayed(run, 4000);
    }
}
