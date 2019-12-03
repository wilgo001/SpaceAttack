package com.iutdelaval.spaceattack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {

    public MediaPlayer introMusic;
    public static final String SPACEATTACK_LEVEL = "com.iutdelaval.spaceattack.LEVEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        introMusic = MediaPlayer.create(this.getApplicationContext(), R.raw.music);
        introMusic.setLooping(true);
    }

    public void chooseLevel(View v) {
        String text = v.toString();
        String id = text.substring(text.length()-2, text.length()-1);
        int level = Integer.parseInt(id);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(SPACEATTACK_LEVEL, level);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        introMusic.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        introMusic.start();
    }
}
