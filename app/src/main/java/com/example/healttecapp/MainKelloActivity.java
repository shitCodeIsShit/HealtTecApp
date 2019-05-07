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

public double jako;
public int aika2;
public int tunnit;
public double minuutit;


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
        this.minuutit = minuutit / 60.0;
        this.jako = Math.round(this.minuutit);
        int i = (int)jako;
        this.aika2 = tunnit + i;



        System.out.println("--------------" + this.aika2 + "------------");

        if(aika2 >= 0 && aika2 < 5) {
            aika2 = 1;
        } else if (aika2 >=5 && aika2 < 7){
            aika2 = 3;
        } else if (aika2 >=7 && aika2 < 9){
            aika2 = 5;
        } else if (aika2 >= 9 && aika2 < 11){
            aika2 = 4;
        } else {
            aika2 = 2;
        }


        System.out.println("----------------" + aika2 + "------------- aika2");


    }
    public void tunnitKantaan(View v){
        ActivityDatabaseAdapter.insertSleepScore(aika2);
        Intent takaisin = new Intent (MainKelloActivity.this, MainActivity.class);
        MainKelloActivity.this.startActivity(takaisin);
    }

}


