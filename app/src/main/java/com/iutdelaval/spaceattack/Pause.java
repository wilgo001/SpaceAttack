package com.iutdelaval.spaceattack;

import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Pause {

    public static final String PAUSE = "PAUSE";
    public static final String PERDU = "PERDU";
    public static final String GAGNE = "GAGNE !";
    private MainActivity context;
    private ImageView background;
    private TextView titre;
    private ImageButton menu;
    private ImageButton replay;
    private ImageButton resume;

    public Pause(final MainActivity context, final String titre) {
        this.context = context;
        this.titre = new TextView(this.context);
        this.titre.setText(titre);
        this.titre.setTextSize(this.context.fenetre.getWidth()/10);
        this.background = new ImageView(this.context);
        this.background.setBackgroundResource(R.drawable.pause);
        int backgroundWidth = this.context.fenetre.getWidth() * 5 / 6;
        int backgroundHeight = this.context.fenetre.getHeight() * 5 / 6;
        this.background.setLayoutParams(new RelativeLayout.LayoutParams(backgroundWidth, backgroundHeight));
        this.background.setX(this.context.fenetre.getWidth()/12);
        this.background.setY(this.context.fenetre.getHeight()/12);
        this.titre.setX(this.background.getX());
        this.titre.setY(this.background.getY());
        this.context.fenetre.addView(this.background);
        this.context.fenetre.addView(this.titre);

        int size = backgroundWidth / 13;
        float y = this.background.getY() + backgroundHeight - size*3;
        float x = this.background.getX() + size;

        this.menu = new ImageButton(this.context);
        this.menu.setBackgroundResource(R.drawable.menu);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(size*3, size*3);
        this.menu.setLayoutParams(params);
        this.menu.setX(x);
        this.menu.setY(y);
        context.fenetre.addView(this.menu);
        this.menu.bringToFront();

        this.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListActivity.class);
                context.startActivity(intent);
            }
        });

        x += size*8;

        this.replay = new ImageButton(this.context);
        this.replay.setBackgroundResource(R.drawable.return_button);
        this.replay.setLayoutParams(params);
        this.replay.setX(x);
        this.replay.setY(y);
        context.fenetre.addView(this.replay);
        this.replay.bringToFront();

        this.replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.recreate();
            }
        });

        if (!titre.equals(this.PERDU)) {
            x -= size*4;

            this.resume = new ImageButton(this.context);
            this.resume.setBackgroundResource(R.drawable.play_button);
            this.resume.setLayoutParams(params);
            this.resume.setX(x);
            this.resume.setY(y);
            context.fenetre.addView(this.resume);
            this.resume.bringToFront();

            this.resume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAlpha(0.0f);
                    if (titre.equals(Pause.PAUSE))
                        context.resume();
                    if (titre.equals(Pause.GAGNE)) {
                        context.goToNextLevel();
                    }
                }
            });
        }

    }

    private void setAlpha(float v) {
        background.setAlpha(v);
        titre.setAlpha(v);
        menu.setAlpha(v);
        replay.setAlpha(v);
        if (resume != null)
            resume.setAlpha(v);
    }
}
