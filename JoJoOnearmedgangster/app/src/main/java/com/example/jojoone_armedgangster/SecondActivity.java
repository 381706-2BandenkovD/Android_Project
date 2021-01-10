package com.example.jojoone_armedgangster;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SecondActivity extends Activity{
    private Button mBack;
    private LinearLayout mBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        mBackground=(LinearLayout)findViewById(R.id.background);
        mBack=(Button)findViewById(R.id.b2);
    }

    public void BG(View view) {
        switch (view.getId()){
            case R.id.b4:
                mBackground.setBackgroundResource(R.drawable.ded);
                break;
            case R.id.b5:
                mBackground.setBackgroundResource(R.drawable.ded3);
                break;
            case R.id.b3:
                mBackground.setBackgroundResource(R.drawable.ded2);
                break;
        }
    }

    public void Back(View view) {
        Intent intent=new Intent(SecondActivity.this,MainActivity.class);
        startActivity(intent);
    }
}