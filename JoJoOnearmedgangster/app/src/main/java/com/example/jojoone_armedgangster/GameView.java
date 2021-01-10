package com.example.jojoone_armedgangster;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable{
    public static int maxX = 20;
    public static int maxY = 28;
    public static float unitW = 0;
    public static float unitH = 0;
    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Monkey monkey;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private ArrayList<Banana> bananas = new ArrayList<>();
    private final int BANANA_INTERVAL = 50;
    private int currentTime = 0;
    private Bitmap image1=BitmapFactory.decodeResource(getResources() ,R.drawable.mback2);
    private Rect srcRect = new Rect(0, 0, image1.getWidth(), image1.getHeight());
    private Rect dstRect = new Rect(srcRect);

    public GameView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            checkCollision();
            checkIfNewBananas();
            control();
        }
    }

    private void update() {
        if(!firstTime) {
            monkey.update();
            for (Banana banana : bananas) {
                banana.update();
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {

            if(firstTime){
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width()/maxX;
                unitH = surfaceHolder.getSurfaceFrame().height()/maxY;

                monkey = new Monkey(getContext());
            }

            canvas = surfaceHolder.lockCanvas();
            canvas.drawBitmap(image1,srcRect,dstRect,null);
            monkey.drow(paint, canvas);
            for(Banana banana : bananas){
                banana.drow(paint, canvas);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkCollision(){
        for (Banana banana : bananas) {
            if(banana.isCollision(monkey.x, monkey.y, monkey.size)){
                gameRunning = false;
            }
        }
    }

    private void checkIfNewBananas(){
        if(currentTime >= BANANA_INTERVAL){
            Banana banana = new Banana(getContext());
            bananas.add(banana);
            currentTime = 0;
        }else{
            currentTime ++;
        }
    }
}
