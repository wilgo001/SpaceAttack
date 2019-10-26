package com.iutdelaval.spaceattack;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Spaceship implements SensorEventListener {

    private ImageButton image;
    private Context context;
    private float xPos;
    private float yPos;
    public SensorManager sensorManager;
    public Sensor accelerometre;
    private ConstraintLayout layout;
    public float maxWidth;
    public float maxHeight;

    public Spaceship(ImageButton image, Context context, float maxHeight, float maxWidth) {
        this.image = image;
        this.context = context;
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
        xPos = 100;
        yPos = 500;
        changePosition();
        sensorManager = (SensorManager) this.context.getSystemService(this.context.SENSOR_SERVICE);
        accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometre, SensorManager.SENSOR_DELAY_FASTEST);
    }
    /*
     *changePosition : changer les coordonnees X et Y du vaisseau
     *
     */
    private void changePosition() {
        boolean inWindow = (xPos <= maxHeight-image.getHeight()-200);
        inWindow = inWindow && (xPos >= 0);
        inWindow = inWindow &&(yPos <= maxWidth-image.getWidth()-200);
        inWindow = inWindow && (yPos >= 0);
        if(inWindow) {
            image.setX(xPos);
            image.setY(yPos);
        }
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
}
