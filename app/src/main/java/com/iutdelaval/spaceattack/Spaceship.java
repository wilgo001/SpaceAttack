package com.iutdelaval.spaceattack;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.iutdelaval.spaceattack.ennemis.Ennemi;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Spaceship implements SensorEventListener {

    private ImageView image;
    private Context context;
    private float xPos;
    private float yPos;
    private Sensor accelerometre;
    private ConstraintLayout layout;
    private float maxHeight;
    private float maxWidth;
    public List<Ennemi> ennemis = new ArrayList<>();

    public Spaceship(Context context, float maxHeight, float maxWidth, ConstraintLayout fenetre) {
        this.layout = fenetre;
        this.context = context;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;

        // création de l'imageView du spaceship
        this.image = new ImageView(context);
        this.image.setBackgroundResource(R.drawable.spaceship);
        this.image.setLayoutParams(new ViewGroup.LayoutParams(128, 128));

        xPos = maxWidth/2;
        yPos = maxHeight/2;

        //ajout de l'imageView au layout
        this.layout.addView(image);

        this.image.setX(xPos);
        this.image.setY(yPos);
        Log.d("debug", this.image.getX()+"/"+this.image.getY());

        SensorManager sensorManager = (SensorManager) this.context.getSystemService(this.context.SENSOR_SERVICE);
        accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometre, SensorManager.SENSOR_DELAY_FASTEST);

        createEnnemis();
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


    private void createEnnemis() {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.println(Log.INFO, "debug", "nouvelle vague d'ennemi");
                float xPos = 0;
                float yPos = -50;
                for (int i = 0; i < 5; i++) {
                    Log.println(Log.INFO, "debug", "nouvel ennemi");
                    ennemis.add(new Ennemi(context, xPos, yPos, layout));
                    Log.println(Log.INFO, "debug", "nouvel ennemi crée");
                    xPos += maxWidth/5;
                }
            }
        };
        timer.schedule(task, 2000);
    }
}
