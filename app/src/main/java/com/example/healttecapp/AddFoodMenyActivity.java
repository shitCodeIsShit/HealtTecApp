package com.example.healttecapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.kello.R;

import java.util.ArrayList;
import java.util.List;

public class AddFoodMenyActivity extends AppCompatActivity {

    private Button saveButton;
    private Spinner healthynesScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_meny);

        saveButton = findViewById(R.id.saveButton);

        // Creating first spinner
        Spinner foodSpinner = findViewById(R.id.eatingTimeSpinner);

        final List<String> eatingTime = new ArrayList<String>();

        eatingTime.add("Aamupala");
        eatingTime.add("Päivällinen");
        eatingTime.add("Välipala");
        eatingTime.add("Lounas");
        eatingTime.add("Iltapala");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, eatingTime);
        foodSpinner.setAdapter(dataAdapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Creating the second spinner
        healthynesScale = findViewById(R.id.scaleOfHealthynes);

        final List<Integer> scale = new ArrayList<>();

        scale.add(1);
        scale.add(2);
        scale.add(3);
        scale.add(4);
        scale.add(5);

        ArrayAdapter<Integer> dataAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, scale);
        healthynesScale.setAdapter(dataAdapter2);

        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    public void insertSpinnerData(View v){
        Spinner sp = findViewById(R.id.scaleOfHealthynes);

        System.out.println("-------------------" + sp.getSelectedItem() + "---selecteditem-------");
        ActivityDatabaseAdapter.insertFoodScore((int)sp.getSelectedItem());

        Intent backHome = new Intent(AddFoodMenyActivity.this, MainActivity.class);
        AddFoodMenyActivity.this.startActivity(backHome);

    }

    public void agasfg(View v){
        ActivityDatabaseAdapter.checkIfDayIsSome();
    }

}
