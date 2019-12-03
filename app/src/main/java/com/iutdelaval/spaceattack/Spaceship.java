package com.iutdelaval.spaceattack;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.iutdelaval.spaceattack.ennemis.Ennemi;
import com.iutdelaval.spaceattack.fire.Fire;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Spaceship implements SensorEventListener {

    private ImageView image;
    private MainActivity context;
    private float xPos;
    private float yPos;
    private Sensor accelerometre;
    private  Runnable firetask;
    private Handler firecount = new Handler();
    private Fire fire;
    public List<Fire> fireList = new ArrayList<>();
    public int fireFrequence = 3000;

    public Spaceship(MainActivity context) {

        this.context = context;

        // création de l'imageView du spaceship
        this.image = new ImageView(context);
        this.image.setBackgroundResource(R.drawable.spaceship);
        start();
    }

    public void start() {
        this.image = new ImageView(context);
        this.image.setBackgroundResource(R.drawable.spaceship);
        this.image.setLayoutParams(new ViewGroup.LayoutParams(context.fenetre.getWidth()/6,context.fenetre.getWidth()/6));

        xPos = context.fenetre.getWidth()/2-context.fenetre.getWidth()/12;
        yPos = context.fenetre.getHeight()/2-context.fenetre.getWidth()/12;

        //ajout de l'imageView au layout
        context.fenetre.addView(image);

        this.image.setX(xPos);
        this.image.setY(yPos);
        setAccelerometre();
        startFire();
        //createFire();
    }

    private void startFire() {
        firetask = new Runnable() {
            @Override
            public void run() {
                if (!context.pause) {
                    fire = new Fire(context, image.getX() + image.getWidth() / 2, image.getY());
                    fireList.add(fire);
                }
                firecount.postDelayed(this, fireFrequence);
            }
        };
        firecount.postDelayed(firetask, 1);

    }


    public void setAccelerometre() {
        SensorManager sensorManager = (SensorManager) this.context.getSystemService(this.context.SENSOR_SERVICE);
        accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometre, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public ImageView getImage() {
        return this.image;
    }


    /*
     *changePosition : changer les coordonnees X et Y du vaisseau
     *
     */
    private void changePosition() {
        if (!context.pause) {
            boolean inWindowX = (xPos <= context.fenetre.getWidth() - image.getWidth());
            inWindowX = inWindowX && (xPos >= 0);
            boolean inWindowY = (yPos <= context.fenetre.getHeight() - image.getHeight() - 100);
            inWindowY = inWindowY && (yPos >= 0);
            if (inWindowX)
                image.setX(xPos);
            if (inWindowY)
                image.setY(yPos);
            xPos = image.getX();
            yPos = image.getY();
            boolean destructionY;
            boolean destructionX;
            for (Ennemi ennemi : context.ennemiList) {
                destructionY = this.image.getY() >= ennemi.getyPos();
                destructionY = destructionY && (this.image.getY() <= ennemi.getyPos() + ennemi.getSize());
                destructionX = this.image.getX() >= ennemi.getyPos();
                destructionX = destructionX && (this.image.getX() <= ennemi.getxPos() + ennemi.getSize());
                if (destructionX && destructionY) {
                    context.setLose();
                }
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            xPos -= sensorEvent.values[0];
            yPos += sensorEvent.values[1];
            changePosition();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
