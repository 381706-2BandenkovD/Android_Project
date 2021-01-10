package com.example.jojoone_armedgangster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public User user = new User("undefined", 0);
    final static String userVariableKey = "USER_VARIABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(userVariableKey, user);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        user = (User)savedInstanceState.getSerializable(userVariableKey);
        TextView dataView = (TextView) findViewById(R.id.dataView);
        dataView.setText("Name: " + user.getName() + " Age: " + user.getAge());
    }
    public void saveData(View view) {
        EditText nameBox = (EditText) findViewById(R.id.nameBox);
        EditText yearBox = (EditText) findViewById(R.id.yearBox);
        String name = nameBox.getText().toString();
        int age = 0;
        try{
            age = Integer.parseInt(yearBox.getText().toString());
        }
        catch (NumberFormatException ex){}
        user = new User(name, age);
    }

    public void getData(View view) {
        TextView dataView = (TextView) findViewById(R.id.dataView);
        dataView.setText("Name: " + user.getName() + " Age: " + user.getAge());
    }

    public void Click(View view) {
        Intent intent=new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
    }

    public void GameClick(View view) {
        if(user.getAge() > 17){
            try{
                Intent intent=new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent);
            }catch (NumberFormatException ex){}
        }

    }

    public void Game2Click(View view) {
        Intent intent=new Intent(MainActivity.this,Game2Activity.class);
        startActivity(intent);
    }
}