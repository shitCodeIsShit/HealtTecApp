package com.example.healttecapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Kukkuu tämä on testi
        // jooo
       // String koodi = "mitä vittuu";//String lisaa = "testi";

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrheiluActivity();
            }
        });
    }

    public void openUrheiluActivity() {
        Intent intent = new Intent(this, MainUrheiluActivity.class);
        startActivity(intent);
    }
}

