package com.example.birzeit_university_student.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.birzeit_university_student.R;

public class MainActivity5_studentHome extends AppCompatActivity {



    private Button button_logout;
    private Button button1_univMap;
    private TextView textView_univ_map_1;
    private TextView textView_univ_map_2;
    private Button button2_faculity;
    private TextView textView_faculity_1;
    private TextView textView_faculity_2;
    private Button button3_payment;
    private TextView textView_payment_1;
    private TextView textView_payment_2;
    private Button button4_cafeteria;
    private TextView textView_cafeteria_1;
    private TextView textView_cafeteria_2;
    private Button button5_task;
    private TextView textView_task_1;
    private TextView textView_task_2;
    private Button button6_grade;
    private TextView textView_grade_1;
    private TextView textView_grade_2;
    private Button button_home;
    private Button button_user;
    private Button button_info;

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity5_studenthome);
        Intent intent = getIntent();
        int id = (int)intent.getExtras().get("stud_id");
        String stud_name=intent.getStringExtra("stud_name");
        // Initialize views
        button_logout=findViewById(R.id.button_logout);

        button1_univMap=findViewById(R.id.button1_univMap);
        textView_univ_map_1=findViewById(R.id.textView_univ_map_1);
        textView_univ_map_2=findViewById(R.id.textView_univ_map_2);
        textView_univ_map_2.setVisibility(View.INVISIBLE);

        button2_faculity=findViewById(R.id.button2_faculity);
        textView_faculity_1=findViewById(R.id.textView_faculity_1);
        textView_faculity_2=findViewById(R.id.textView_faculity_2);
        textView_faculity_2.setVisibility(View.INVISIBLE);

        button3_payment=findViewById(R.id.button3_payment);
        textView_payment_1=findViewById(R.id.textView_payment_1);
        textView_payment_2=findViewById(R.id.textView_payment_2);
        textView_payment_2.setVisibility(View.INVISIBLE);

        button4_cafeteria=findViewById(R.id.button4_cafeteria);
        textView_cafeteria_1=findViewById(R.id.textView_cafeteria_1);
        textView_cafeteria_2=findViewById(R.id.textView_cafeteria_2);
        textView_cafeteria_2.setVisibility(View.INVISIBLE);

        button5_task=findViewById(R.id.button5_task);
        textView_task_1=findViewById(R.id.textView_task_1);
        textView_task_2=findViewById(R.id.textView_task_2);
        textView_task_2.setVisibility(View.INVISIBLE);

        button6_grade=findViewById(R.id.button6_grade);
        textView_grade_1=findViewById(R.id.textView_grade_1);
        textView_grade_2=findViewById(R.id.textView_grade_2);
        textView_grade_2.setVisibility(View.INVISIBLE);

        button_home=findViewById(R.id.button_home);
        button_user=findViewById(R.id.button_user);
        button_info=findViewById(R.id.button_info);


        // SetOnAction

        // logout
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFinishing()) {
                    alertDialog = new AlertDialog.Builder(MainActivity5_studentHome.this)
                            .setTitle("Log Out")
                            .setMessage("Are you sure you want to log out ?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity5_studentHome.this, MainActivity1_login.class);
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


        button1_univMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_univ_map_1.setVisibility(View.INVISIBLE);
                textView_univ_map_2.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity5_studentHome.this, MainActivity8_1_BzuMap.class);
                        intent.putExtra("stud_id", id);
                        startActivity(intent);

                    }
                }, 100);
            }
        });

        button2_faculity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_faculity_1.setVisibility(View.INVISIBLE);
                textView_faculity_2.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity5_studentHome.this, MainActivity9_1_faculites.class);
                        intent.putExtra("stud_id", id);
                        startActivity(intent);

                    }
                }, 100);
            }
        });

        button3_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_payment_1.setVisibility(View.INVISIBLE);
                textView_payment_2.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity5_studentHome.this,MainActivity10_payment.class);
                        intent.putExtra("stud_id", id);
                        startActivity(intent);

                    }
                }, 100);
            }
        });

        button4_cafeteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_cafeteria_1.setVisibility(View.INVISIBLE);
                textView_cafeteria_2.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity5_studentHome.this, MainActivity11_1_cafeteria.class);
                        intent.putExtra("stud_id", id);
                        intent.putExtra("stud_name",stud_name);
                        startActivity(intent);

                    }
                }, 100);
            }
        });

        button5_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_task_1.setVisibility(View.INVISIBLE);
                textView_task_2.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity5_studentHome.this, MainActivity12_1_taskHome.class);
                        intent.putExtra("stud_id", id);
                        startActivity(intent);

                    }
                }, 100);
            }
        });

        button6_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_grade_1.setVisibility(View.INVISIBLE);
                textView_grade_2.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity5_studentHome.this,MainActivity13_grade.class);
                        intent.putExtra("stud_id", id);
                        startActivity(intent);
                    }
                }, 100);
            }
        });

        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity5_studentHome.this, MainActivity5_studentHome.class);
                intent.putExtra("stud_id", id);
                startActivity(intent);
                finish();
            }
        });

        button_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity5_studentHome.this, MainActivity6_studentDetails.class);
                intent.putExtra("stud_id", id);
                startActivity(intent);
            }
        });

        button_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity5_studentHome.this, MainActivity7_info.class);
                intent.putExtra("stud_id", id);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (!isFinishing()) {
            alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Log Out")
                    .setMessage("Are you sure you want to log out ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity5_studentHome.this, MainActivity1_login.class);
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
        } else {
            super.onBackPressed();
        }


    }



    @Override
    protected void onPause() {
        super.onPause();
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }
}
