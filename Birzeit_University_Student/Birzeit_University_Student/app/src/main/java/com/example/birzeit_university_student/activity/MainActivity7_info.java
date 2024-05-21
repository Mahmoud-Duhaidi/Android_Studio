package com.example.birzeit_university_student.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.birzeit_university_student.R;

public class MainActivity7_info extends AppCompatActivity {
    private Button button_home;
    private Button button_user;
    private Button button_info;
    private Button button_logout;
    private AlertDialog alertDialog;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity7_info);

        Intent intent = getIntent();
        id = (int)intent.getExtras().get("stud_id");

        button_home=findViewById(R.id.button_home);
        button_user=findViewById(R.id.button_user);
        button_info=findViewById(R.id.button_info);
        button_logout=findViewById(R.id.button_logout);

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFinishing()) {
                    alertDialog = new AlertDialog.Builder(MainActivity7_info.this)
                            .setTitle("Log Out")
                            .setMessage("Are you sure you want to log out ?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity7_info.this, MainActivity1_login.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(R.drawable.logout)
                            .show();

                    TextView messageTextView = alertDialog.findViewById(android.R.id.message);
                    if (messageTextView != null) {
                        messageTextView.setTextColor(Color.BLACK);
                    }

                    Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    if (positiveButton != null) {
                        positiveButton.setTextColor(Color.BLACK);
                    }

                    Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                    if (negativeButton != null) {
                        negativeButton.setTextColor(Color.BLACK);
                    }
                }
            }
        });

        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity7_info.this, MainActivity5_studentHome.class);
                intent.putExtra("stud_id", id);
                startActivity(intent);
                finish();
            }
        });

        button_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity7_info.this, MainActivity6_studentDetails.class);
                intent.putExtra("stud_id", id);
                startActivity(intent);
                finish();
            }
        });

        button_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity7_info.this, MainActivity7_info.class);
                intent.putExtra("stud_id", id);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity7_info.this, MainActivity5_studentHome.class);
        intent.putExtra("stud_id", id);
        startActivity(intent);
        finish();
    }
}
