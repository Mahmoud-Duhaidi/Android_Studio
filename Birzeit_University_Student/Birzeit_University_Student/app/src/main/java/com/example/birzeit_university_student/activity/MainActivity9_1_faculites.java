package com.example.birzeit_university_student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.birzeit_university_student.R;

public class MainActivity9_1_faculites extends AppCompatActivity {
    private Button button1_Education_Bachelor;
    private Button button2_BusinessEconomics_Bachelor;
    private Button button3_Literature_Bachelor;
    private Button button4_Nursing_Bachelor;
    private Button button5_law_Bachelor;
    private Button button6_Engineering_Technology_Bachelor;
    private Button button7_Art_Bachelor;
    private Button button8_Science_Bachelor;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity9_1_faculites);
        Intent intent = getIntent();
        id = (int)intent.getExtras().get("stud_id");

        button1_Education_Bachelor=findViewById(R.id.button1_Education_Bachelor);
        button2_BusinessEconomics_Bachelor=findViewById(R.id.button2_BusinessEconomics_Bachelor);
        button3_Literature_Bachelor=findViewById(R.id.button3_Literature_Bachelor);
        button4_Nursing_Bachelor=findViewById(R.id.button4_Nursing_Bachelor);
        button5_law_Bachelor=findViewById(R.id.button5_law_Bachelor);
        button6_Engineering_Technology_Bachelor=findViewById(R.id.button6_Engineering_Technology_Bachelor);
        button7_Art_Bachelor=findViewById(R.id.button7_Art_Bachelor);
        button8_Science_Bachelor=findViewById(R.id.button8_Science_Bachelor);


        button1_Education_Bachelor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity9_1_faculites.this, MainActivity9_2_faculitesDetails.class);
                intent.putExtra("stud_id", id);
                intent.putExtra("stud_fac", "Education-Bachelor");
                startActivity(intent);
            }
        });

        button2_BusinessEconomics_Bachelor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity9_1_faculites.this, MainActivity9_2_faculitesDetails.class);
                intent.putExtra("stud_id", id);
                intent.putExtra("stud_fac", "BusinessEconomics-Bachelor");
                startActivity(intent);
            }
        });

        button3_Literature_Bachelor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity9_1_faculites.this, MainActivity9_2_faculitesDetails.class);
                intent.putExtra("stud_id", id);
                intent.putExtra("stud_fac", "Literature-Bachelor");
                startActivity(intent);
            }
        });

        button4_Nursing_Bachelor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity9_1_faculites.this, MainActivity9_2_faculitesDetails.class);
                intent.putExtra("stud_id", id);
                intent.putExtra("stud_fac", "Nursing-Bachelor");
                startActivity(intent);
            }
        });

        button5_law_Bachelor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity9_1_faculites.this, MainActivity9_2_faculitesDetails.class);
                intent.putExtra("stud_id", id);
                intent.putExtra("stud_fac", "law-Bachelor");
                startActivity(intent);
            }
        });

        button6_Engineering_Technology_Bachelor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity9_1_faculites.this, MainActivity9_2_faculitesDetails.class);
                intent.putExtra("stud_id", id);
                intent.putExtra("stud_fac", "Engineering-Technology-Bachelor");
                startActivity(intent);
            }
        });

        button7_Art_Bachelor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity9_1_faculites.this, MainActivity9_2_faculitesDetails.class);
                intent.putExtra("stud_id", id);
                intent.putExtra("stud_fac", "Art-Bachelor");
                startActivity(intent);
            }
        });

        button8_Science_Bachelor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity9_1_faculites.this, MainActivity9_2_faculitesDetails.class);
                intent.putExtra("stud_id", id);
                intent.putExtra("stud_fac", "Science-Bachelor");
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity9_1_faculites.this, MainActivity5_studentHome.class);
        intent.putExtra("stud_id", id);
        startActivity(intent);
        finish();
    }
}
