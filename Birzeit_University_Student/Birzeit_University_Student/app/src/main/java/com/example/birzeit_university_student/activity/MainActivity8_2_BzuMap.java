package com.example.birzeit_university_student.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.birzeit_university_student.R;

public class MainActivity8_2_BzuMap extends AppCompatActivity {
    private ImageView bzumap;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity8_1_bzumap);
        Intent intent = getIntent();
        id = (int) intent.getExtras().get("stud_id");

        bzumap = findViewById(R.id.bzumap);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity8_2_BzuMap.this, MainActivity5_studentHome.class);
        intent.putExtra("stud_id", id);
        startActivity(intent);
        finish();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(this, MainActivity8_1_BzuMap.class);
            intent.putExtra("stud_id", id);
            startActivity(intent);
        }
    }


}
