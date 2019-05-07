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
double aika;
public int aika2;
int tunnit;
int minuutit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aika);

        Button button = findViewById(R.id.button);
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
        this.tunnit = tunnit;
        this.minuutit = minuutit / 60;
        this.aika = this.tunnit + this.minuutit;
        this.aika2 = Math.round(this.tunnit + this.minuutit);
        if(this.aika2 >= 0 && this.aika2 <= 5){
            this.aika2 = 1;
        }
        if(this.aika2 > 5 && this.aika2 < 7 ){
            this.aika2 = 3;
        }
        if(this.aika2 > 7 && this.aika2 < 9 ){
            this.aika2 = 5;
        }
        if(this.aika2 > 9 && this.aika2 < 11){
            this.aika = 4;
        }else{
            this.aika2 = 2;
        }


        System.out.println("----------------" + aika2 + "------------- aika2");


    }
    public void tunnitKantaan(View v){
        ActivityDatabaseAdapter.insertSleepScore(aika2);
        Intent takaisin = new Intent (MainKelloActivity.this, MainActivity.class);
        MainKelloActivity.this.startActivity(takaisin);
    }

}


