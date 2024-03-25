package com.example.millionairequiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // this starts the app
    public void startGame(View view) {
        // will navigate to QuestionOneActivity and allow the game to start
        startActivity(new Intent(MainActivity.this, QuestionOneActivity.class));
    }
}