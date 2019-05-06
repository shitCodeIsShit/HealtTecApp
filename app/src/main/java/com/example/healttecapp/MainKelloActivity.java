package com.example.healttecapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;



public class MainKelloActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aika);

        Button button = findViewById(R.id.button);
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openMain();

            }
        });
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new AjanValitsin();
                timePicker.show(getSupportFragmentManager(), "ajan valitsin");
            }
        });
    }
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onTimeSet(TimePicker view, int tunnit, int minuutit) {
        TextView textView = findViewById(R.id.textView);
        textView.setText("Tunteja: " + tunnit + " Minuutteja: " + minuutit);

    }
}


