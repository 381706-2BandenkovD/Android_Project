package com.example.jojoone_armedgangster;

import android.content.Context;

public class Monkey extends MonkeyBody {

    public Monkey(Context context) {
        bitmapId = R.drawable.monkey;
        size = 5;
        x=7;
        y=GameView.maxY - size - 1;
        speed = (float) 0.2;
        init(context);
    }

    @Override
    public void update() {
        if(Game2Activity.isLeftPressed && x >= 0){
            x -= speed;
        }
        if(Game2Activity.isRightPressed && x <= GameView.maxX - 5){
            x += speed;
        }
    }
}
