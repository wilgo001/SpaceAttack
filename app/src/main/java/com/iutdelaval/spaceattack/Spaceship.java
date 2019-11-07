package com.iutdelaval.spaceattack;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.iutdelaval.spaceattack.ennemis.Ennemi;
import com.iutdelaval.spaceattack.fire.Fire;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Spaceship implements SensorEventListener {

    private ImageButton image;
    private MainActivity context;
    private float xPos;
    private float yPos;
    private Sensor accelerometre;
    private RelativeLayout layout;
    private float maxHeight;
    private float maxWidth;
    public List<Fire> fires = new ArrayList<>();

    public Spaceship(MainActivity context) {
        this.layout = context.relativeLayout;
        this.context = context;
        this.maxWidth = context.maxWidth;
        this.maxHeight = context.maxHeight;

        // création de l'imageView du spaceship
        this.image = new ImageButton(context);
        this.image.setBackgroundResource(R.drawable.spaceship);
        this.image.setLayoutParams(new ViewGroup.LayoutParams(((int)this.maxWidth/6),((int) this.maxWidth/6)));

        xPos = maxWidth/2;
        yPos = maxHeight/2;

        //ajout de l'imageView au layout
        this.layout.addView(image);

        this.image.setX(xPos);
        this.image.setY(yPos);
        Log.d("debug", this.image.getX()+"/"+this.image.getY());
        fire();
    }

    private void fire() {
        Timer timerFire = new Timer();
        TimerTask timerTaskFire = new TimerTask() {
            @Override
            public void run() {
                context.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        fires.add(new Fire(context, image.getX(), image.getY()));

                    }
                });
            }
        };
        Timer timerMove = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(Fire fire : fires) {
                            if (fire.getImage().getY() - 1 <= 0-fire.getImage().getWidth())
                            fire.getImage().setY(fire.getImage().getY() - 1);
                        }
                    }
                });
            }
        };
    }

    public void setAccelerometre() {
        SensorManager sensorManager = (SensorManager) this.context.getSystemService(this.context.SENSOR_SERVICE);
        Log.println(Log.INFO, "debug", "sensorManager créer");
        accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Log.println(Log.INFO,"debug", "accelerometre créer");
        sensorManager.registerListener(this, accelerometre, SensorManager.SENSOR_DELAY_FASTEST);
        Log.println(Log.INFO,"debug", "sensor Accelerometre register");
    }

    public ImageButton getImage() {
        return this.image;
    }
    /*
     *changePosition : changer les coordonnees X et Y du vaisseau
     *
     */
    private void changePosition() {
        boolean inWindowX = (xPos <= maxWidth -image.getWidth());
        inWindowX = inWindowX && (xPos >= 0);
        boolean inWindowY = (yPos <= maxHeight -image.getHeight()-100);
        inWindowY = inWindowY && (yPos >= 0);
        if(inWindowX)
            image.setX(xPos);
        if(inWindowY)
            image.setY(yPos);
        xPos=image.getX();
        yPos=image.getY();
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

    public void destroy() {

        image = null;
    }

    private boolean contact() {
        for(Fire ennemi : fires) {
          /*  boolean meeting = ((ennemi.getxPos()+ ennemi.getImage().getWidth())== xPos-image.getWidth());
            meeting = meeting || (ennemi.getxPos() == xPos);
            meeting = meeting || (ennemi.getyPos() == yPos);
            meeting = meeting || (ennemi.getyPos()+ennemi.getImage().getHeight() == yPos-image.getHeight());
            if(meeting) {
                image = null;
                return true;
            }*/
        }
        return true;
    }
}
