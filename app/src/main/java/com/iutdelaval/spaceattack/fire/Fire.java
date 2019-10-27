package com.iutdelaval.spaceattack.fire;

import android.content.Context;
import android.widget.ImageView;

public class Fire {

    private ImageView image;
    private Context context;
    private final float xPos;
    private float yPos;



    public Fire(Context context, float xPos, float yPos) {
        this.context = context;
        this.xPos = xPos;
        this.yPos = yPos;
        this.image = new ImageView(this.context);
        image.setX(xPos);
        image.setY(yPos);
    }
}


