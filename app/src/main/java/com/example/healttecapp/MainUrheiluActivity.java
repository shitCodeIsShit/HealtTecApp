package com.example.healttecapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.example.healttecapp.ActivityDatabaseAdapter.*;
import static com.example.healttecapp.R.id.textViewUrheiluArvo;


public class MainUrheiluActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_urheilu);

        SeekBar seekBar;
        seekBar = findViewById(R.id.SeekBarUrheilu);
        final TextView textViewUrheiluArvo = findViewById(R.id.textViewUrheiluArvo);
        Button PeruutaButtonUrheilu = findViewById(R.id.PeruutaButtonUrheilu);
        Button TallennaButtonUrheilu = findViewById(R.id.TallennaButtonUrheilu);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewUrheiluArvo.setText(String.valueOf(progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final int moro = seekBar.getProgress()+1;

        TallennaButtonUrheilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityDatabaseAdapter.insertActivityScore(moro);
            }
        });



          PeruutaButtonUrheilu.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                          Intent intent = new Intent(MainUrheiluActivity.this, MainActivity.class);
                  MainUrheiluActivity.this.startActivity(intent);
              }
          });

        }

    }
















