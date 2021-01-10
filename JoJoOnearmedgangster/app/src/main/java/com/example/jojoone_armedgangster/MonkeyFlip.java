package com.example.jojoone_armedgangster;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MonkeyFlip extends AppCompatActivity {
    public static VideoView videoPlayer;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        videoPlayer =  (VideoView)findViewById(R.id.videoPlayer);
        Uri myVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.monkeyflip);
        videoPlayer.setVideoURI(myVideoUri);
        videoPlayer.start();
    }

    public void Backnu(View view) {
        Intent intent=new Intent(MonkeyFlip.this,Game2Activity.class);
        startActivity(intent);
    }

    public void Backu(View view) {
        Intent intent=new Intent(MonkeyFlip.this,MainActivity.class);
        startActivity(intent);
    }
}
