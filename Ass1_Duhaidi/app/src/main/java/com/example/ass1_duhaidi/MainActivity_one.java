package com.example.ass1_duhaidi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;


public class MainActivity_one extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        Button addNewTask=findViewById(R.id.AddNewTask);
        Button viewDueTask=findViewById(R.id.ViewDueTask);
        Button viewDoneTask=findViewById(R.id.ViewDoneTask);
        addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_one.this, MainActivity_two.class);
                startActivity(intent);
            }
        });

        viewDueTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_one.this, MainActivity_three.class);
                startActivity(intent);

            }
        });

        viewDoneTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_one.this, MainActivity_four.class);
                startActivity(intent);
            }
        });

    }
}