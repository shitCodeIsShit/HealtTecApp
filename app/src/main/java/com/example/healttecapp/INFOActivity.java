package com.example.healttecapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class INFOActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button PalaaButton = findViewById(R.id.palaainfostabutton);

        PalaaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });
    }


    public void openMain() {
        Intent backHome = new Intent(INFOActivity.this, MainActivity.class);
        INFOActivity.this.startActivity(backHome);
    }



}
