package com.iutdelaval.spaceattack;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Spaceship implements SensorEventListener {

    private ImageView image;
    private Context context;
    private float xPos;
    private float yPos;
    private Sensor accelerometre;
    private ConstraintLayout layout;
    private float maxHeight;
    private float maxWidth;

    public Spaceship(Context context, float maxHeight, float maxWidth, ConstraintLayout fenetre) {
        this.layout = fenetre;
        this.context = context;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;

        // création de l'imageView du spaceship
        this.image = new ImageView(context);
        this.image.setBackgroundResource(R.drawable.spaceship);
        this.image.setLayoutParams(new ViewGroup.LayoutParams(128, 128));

        //ajout de l'imageView au layout
        this.layout.addView(image);
        xPos = maxWidth/2;
        yPos = maxHeight/2;
        this.image.setX(xPos);
        this.image.setX(yPos);
        SensorManager sensorManager = (SensorManager) this.context.getSystemService(this.context.SENSOR_SERVICE);
        accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometre, SensorManager.SENSOR_DELAY_FASTEST);
    }
    /*
     *changePosition : changer les coordonnees X et Y du vaisseau
     *
     */
    private void changePosition() {
        boolean inWindowX = (xPos <= maxWidth -image.getWidth());
        inWindowX = inWindowX && (xPos >= 0);
        boolean inWindowY = (yPos <= maxHeight -image.getHeight());
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
}
