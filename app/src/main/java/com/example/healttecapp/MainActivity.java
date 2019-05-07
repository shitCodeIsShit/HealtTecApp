package com.example.healttecapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    ActivityDatabaseAdapter activityDatabaseAdapter;
    private ImageView paaUkko;
    private Button button3;
    private Button button2;
    private Button button4;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityDatabaseAdapter = new ActivityDatabaseAdapter(getApplicationContext());

        paaUkko = findViewById(R.id.maskotti);
        PisteLaskuri p = new PisteLaskuri();
        paaUkko = p.mikaMaskotti(paaUkko);

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
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRuokailu();
            }
        });
        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTiedot();
            }
        });


    }
    public void openKello() {
        Intent intentKello = new Intent(this, MainKelloActivity.class);
        startActivity(intentKello);
    }

    public void openUrheiluActivity() {
        Intent intentUrheilu = new Intent(this, MainUrheiluActivity.class);
        startActivity(intentUrheilu);
    }

    public void openRuokailu(){
        Intent intentRuokailu = new Intent (this, AddFoodMenyActivity.class);
        startActivity(intentRuokailu);
    }

    public void openTiedot(){
        Intent intentTiedot = new Intent(this, TiedotActivity.class);
        startActivity(intentTiedot);
    }
}

