package com.example.healttecapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TiedotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiedot);

        ListView lv = findViewById(R.id.listViewMaxPisteet);

        lv.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                ActivityDatabaseAdapter.getAllTotalPoints()
        ));

    }

    public void getBackToMainMenu(View view) {
        Intent backHome = new Intent(TiedotActivity.this, MainActivity.class);
        TiedotActivity.this.startActivity(backHome);
    }

}
