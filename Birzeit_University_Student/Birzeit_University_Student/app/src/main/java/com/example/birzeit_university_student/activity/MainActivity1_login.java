package com.example.birzeit_university_student.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.birzeit_university_student.classes.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity1_login extends AppCompatActivity {
    private EditText editTextstudId;
    private ImageView error_image1;
    private EditText editTextPassword;
    private ImageView error_image2;
    private TextView error_message;
    private CheckBox checkBox;
    private Button buttonLogin;

    private RequestQueue queue;

    // Set saved data if remember checkbox is checked
    String PREF_NAME = "MyPrefs";
    String KEY_stud_id = "stud_id";
    String KEY_PASSWORD = "password";
    String KEY_REMEMBER = "remember";

    SharedPreferences prefs_remember_me;

    private AlertDialog alertDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_login);




        queue = Volley.newRequestQueue(this);
        prefs_remember_me = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Initialize views
        editTextstudId=findViewById(R.id.editTextstudId);
        error_image1=findViewById(R.id.error_image1);
        error_image1.setVisibility(View.INVISIBLE);

        editTextPassword=findViewById(R.id.editTextPassword);
        error_image2=findViewById(R.id.error_image2);
        error_image2.setVisibility(View.INVISIBLE);

        error_message=findViewById(R.id.error_message);

        checkBox=findViewById(R.id.checkBox);
        buttonLogin=findViewById(R.id.buttonLogin);



        if (prefs_remember_me.getBoolean(KEY_REMEMBER, false)) {
            editTextstudId.setText(prefs_remember_me.getString(KEY_stud_id, ""));
            editTextPassword.setText(prefs_remember_me.getString(KEY_PASSWORD, ""));
            checkBox.setChecked(true);
        }

        //************* action


        // Login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stud_id = editTextstudId.getText().toString();
                String stud_password = editTextPassword.getText().toString();


                // if the fields not fill
                if(stud_id.isEmpty() || stud_password.isEmpty()){
                    error_message.setText("Please fill in all fields");
                    // if user name not fill
                    if(stud_id.isEmpty()){
                        error_image1.setVisibility(View.VISIBLE);
                    }
                    else{
                        error_image1.setVisibility(View.INVISIBLE);
                    }

                    // if password not fill
                    if(stud_password.isEmpty()){
                        error_image2.setVisibility(View.VISIBLE);
                    }
                    else{
                        error_image2.setVisibility(View.INVISIBLE);
                    }
                }
                else if(editTextstudId.getText().toString().equals("admin") && editTextPassword.getText().toString().equals("admin")){
                    error_message.setText("");

                    error_image1.setVisibility(View.INVISIBLE);
                    error_image2.setVisibility(View.INVISIBLE);

                    // Save data if remember checkbox is checked
                    if (checkBox.isChecked()) {
                        SharedPreferences.Editor editor = prefs_remember_me.edit();
                        editor.putString(KEY_stud_id, editTextstudId.getText().toString());
                        editor.putString(KEY_PASSWORD, editTextPassword.getText().toString());
                        editor.putBoolean(KEY_REMEMBER, true);
                        editor.apply();
                    } else {
                        SharedPreferences.Editor editor = prefs_remember_me.edit();
                        editor.clear();
                        editor.apply();
                    }

                    Intent intent = new Intent(MainActivity1_login.this, MainActivity2_adminHome.class);
                    startActivity(intent);
                }
                else{

                    isValidStudent(Integer.parseInt(stud_id),stud_password);

                }

            }
        });


    }

    public void isValidStudent(int id, String password) {
        String url = "http://10.0.2.2:5000/student/" + id;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int studId = response.getInt("stud_id");
                    String studPassword = response.getString("stud_password");
                    String studName = response.getString("stud_name");



                    // if Student ID or Password uncorrected
                    if(!(editTextstudId.getText().toString().equals(studId+"") && editTextPassword.getText().toString().equals(studPassword))){
                        error_message.setText("Student id or Password is incorrect");

                        error_image1.setVisibility(View.VISIBLE);
                        error_image2.setVisibility(View.VISIBLE);
                    }
                    else{
                        error_message.setText("");

                        error_image1.setVisibility(View.INVISIBLE);
                        error_image2.setVisibility(View.INVISIBLE);

                        // Save data if remember checkbox is checked
                        if (checkBox.isChecked()) {
                            SharedPreferences.Editor editor = prefs_remember_me.edit();
                            editor.putString(KEY_stud_id, studId+"");
                            editor.putString(KEY_PASSWORD, studPassword);
                            editor.putBoolean(KEY_REMEMBER, true);
                            editor.apply();
                        } else {
                            SharedPreferences.Editor editor = prefs_remember_me.edit();
                            editor.clear();
                            editor.apply();
                        }

                        Intent intent = new Intent(MainActivity1_login.this, MainActivity5_studentHome.class);
                        intent.putExtra("stud_id", studId);
                        intent.putExtra("stud_name", studName);
                        startActivity(intent);
                        finish();
                    }

                } catch (JSONException exception) {

                    Log.d("Error", exception.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error_message.setText("Student id or Password is incorrect");

                error_image1.setVisibility(View.VISIBLE);
                error_image2.setVisibility(View.VISIBLE);
            }
        });

        queue.add(request);

    }

    @Override
    public void onBackPressed() {
        if (!isFinishing()) {
            alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Exit")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
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