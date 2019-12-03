package com.iutdelaval.spaceattack.fire;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.iutdelaval.spaceattack.MainActivity;
import com.iutdelaval.spaceattack.R;

public class Laser {

    private MainActivity context;
    private ImageView image;
    private int xPos;
    private int yPos;

    public Laser(MainActivity context, int xPos, int yPos) {
        this.context = context;
        this.xPos = xPos;
        this.yPos = yPos;
        this.image = new ImageView(context);
        alert();
        this.image.setAlpha(0f);
        this.image.setX(this.xPos);
        this.image.setY(this.yPos);
        this.context.fenetre.addView(image);
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
        this.image.setX(xPos);
    }

    public void alert() {
        this.image.setAlpha(1f);
        this.image.setBackgroundResource(R.drawable.alert);
        int size = this.context.fenetre.getWidth()/5;
        this.image.setLayoutParams(new RelativeLayout.LayoutParams(size, size));
    }

    public void laser() {
        this.image.setBackgroundResource(R.drawable.laser);
        int size = this.context.fenetre.getWidth()/5;
        this.image.setLayoutParams(new RelativeLayout.LayoutParams(size, this.context.fenetre.getHeight()));
        ImageView spaceship = context.spaceship.getImage();
        if (spaceship.getX() + spaceship.getWidth() >= this.image.getX() || spaceship.getX() <= this.image.getX() + size) {
            context.setLose();
        }
    }

    public void stop() {
        this.image.setAlpha(0f);
    }
}
