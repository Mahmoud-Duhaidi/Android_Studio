package com.example.birzeit_university_student.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.birzeit_university_student.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity6_studentDetails extends AppCompatActivity {

    private TextView textViewStud_Id;
    private TextView textViewStud_name;
    private TextView textViewStud_email;
    private TextView textViewStud_password;
    private TextView textViewStud_major;

    private Button button_home;
    private Button button_user;
    private Button button_info;
    private Button button_logout;
    private RequestQueue queue;
    private AlertDialog alertDialog;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity6_studentdetails);
        queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        id = (int)intent.getExtras().get("stud_id");

        // Initialize views
        textViewStud_Id=findViewById(R.id.textViewStud_Id);
        textViewStud_Id.setText(id+"");
        textViewStud_name=findViewById(R.id.textViewStud_name);
        textViewStud_email=findViewById(R.id.textViewStud_email);
        textViewStud_password=findViewById(R.id.textViewStud_password);
        textViewStud_major=findViewById(R.id.textViewStud_major);

        button_home=findViewById(R.id.button_home);
        button_user=findViewById(R.id.button_user);
        button_info=findViewById(R.id.button_info);
        button_logout=findViewById(R.id.button_logout);

        String url = "http://10.0.2.2:5000/student/" + id;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int studId = response.getInt("stud_id");
                    String studName = response.getString("stud_name");
                    String studEmail = response.getString("stud_email");
                    String studPassword = response.getString("stud_password");
                    String studMajor = response.getString("stud_major");

                    textViewStud_name.setText(studName);
                    textViewStud_email.setText(studEmail);
                    textViewStud_password.setText(studPassword);
                    textViewStud_major.setText(studMajor);

                } catch (JSONException exception) {
                    Log.d("Error", exception.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity6_studentDetails.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
                Log.d("Error_json", error.toString());
            }
        });

        queue.add(request);

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFinishing()) {
                    alertDialog = new AlertDialog.Builder(MainActivity6_studentDetails.this)
                            .setTitle("Log Out")
                            .setMessage("Are you sure you want to log out ?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity6_studentDetails.this, MainActivity1_login.class);
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
                Intent intent = new Intent(MainActivity6_studentDetails.this, MainActivity5_studentHome.class);
                intent.putExtra("stud_id", id);
                startActivity(intent);
                finish();
            }
        });

        button_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity6_studentDetails.this, MainActivity6_studentDetails.class);
                intent.putExtra("stud_id", id);
                startActivity(intent);
                finish();
            }
        });

        button_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity6_studentDetails.this, MainActivity7_info.class);
                intent.putExtra("stud_id", id);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity6_studentDetails.this, MainActivity5_studentHome.class);
        intent.putExtra("stud_id", id);
        startActivity(intent);
        finish();
    }
}
