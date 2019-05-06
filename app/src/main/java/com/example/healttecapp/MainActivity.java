package com.example.healttecapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kello.R;

public class MainActivity extends AppCompatActivity {

    private Button button3;
    private Button button2;
    private Button button4;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKello();
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrheiluActivity();
            }
        });
    }
    public void openKello() {
        Intent intent = new Intent(this, MainKelloActivity.class);
        startActivity(intent);
    }

    public void openUrheiluActivity() {
        Intent intent = new Intent(this, MainUrheiluActivity.class);
        startActivity(intent);
    }
}

