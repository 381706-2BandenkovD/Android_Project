package com.example.jojoone_armedgangster;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import static java.lang.Integer.valueOf;

public class GameActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    private TextView msg;
    private ImageView img1, img2, img3;
    private Wheel wheel1, wheel2, wheel3;
    private Button btn;
    private boolean isStarted;
    private Button mBack;
    private TextView mTextView;
    private TextView setMoney;
    public Integer stavka = 0;
    public Integer helper = 0;
    public Integer cash = 1000;

    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        btn = (Button) findViewById(R.id.btn);
        msg = (TextView) findViewById(R.id.msg);

        final SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this);

        mTextView = (TextView)findViewById(R.id.textView);
        setMoney = (TextView)findViewById(R.id.textVin);
        mTextView.setText("0");
        setMoney.setText("Ваши деньги: " + String.valueOf(cash));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStarted) {
                    wheel1.stopWheel();
                    wheel2.stopWheel();
                    wheel3.stopWheel();

                    if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex == wheel3.currentIndex) {
                        helper = 0;
                        cash = cash - stavka;
                        helper =stavka * 2;
                        cash = cash + helper;
                        msg.setText("You win (x2) " + String.valueOf(helper));
                        setMoney.setText("Ваши деньги: " + String.valueOf(cash));
                    } else if (wheel1.currentIndex == wheel2.currentIndex || wheel2.currentIndex == wheel3.currentIndex
                            || wheel1.currentIndex == wheel3.currentIndex) {
                        helper = 0;
                        cash = cash - stavka;
                        helper = stavka + 10;
                        cash = cash + helper;
                        msg.setText("You win (+10) "+ String.valueOf(helper));
                        setMoney.setText("Ваши деньги: " + String.valueOf(cash));
                    } else {
                        helper = 0;
                        cash = cash - stavka;
                        stavka = 0;
                        msg.setText("You lose");
                        setMoney.setText("Ваши деньги: " + String.valueOf(cash));
                    }

                    btn.setText("Start");
                    isStarted = false;

                } else {

                    wheel1 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img1.setImageResource(img);
                                }
                            });
                        }
                    }, 200, randomLong(0, 200));

                    wheel1.start();

                    wheel2 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img2.setImageResource(img);
                                }
                            });
                        }
                    }, 200, randomLong(150, 400));

                    wheel2.start();

                    wheel3 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img3.setImageResource(img);
                                }
                            });
                        }
                    }, 200, randomLong(150, 400));

                    wheel3.start();

                    btn.setText("Stop");
                    msg.setText("");
                    setMoney.setText("Ваши деньги: " + String.valueOf(cash - stavka));
                    isStarted = true;
                }
            }
        });
        mBack=(Button)findViewById(R.id.b2);
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        stavka = valueOf(progress);
        mTextView.setText(String.valueOf(stavka));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void Backmen(View view) {
        Intent intent=new Intent(GameActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
