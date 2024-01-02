package com.example.ass2_duhaidi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Registration extends AppCompatActivity {

    private ArrayList<User> list_user;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static final String DATA = "DATA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupSharedPrefs();
        list_user=new ArrayList<>();

        EditText usernameEditText = findViewById(R.id.usernamereg);
        EditText emailEditText = findViewById(R.id.useremailreg);
        EditText passwordEditText = findViewById(R.id.userpasswordreg);
        EditText passwordConfirmationEditText = findViewById(R.id.userpasswordconfirmationreg);

        TextView haveAccountTextView = findViewById(R.id.gotologin);
        haveAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this, Login.class));
            }
        });

        Button registerButton = findViewById(R.id.btnRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString();
                String passwordConfirmation = passwordConfirmationEditText.getText().toString();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
                    Toast.makeText(Registration.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (!password.equals(passwordConfirmation)) {
                    passwordEditText.setError("Passwords do not match");
                    passwordConfirmationEditText.setError("Passwords do not match");
                    return;
                }
                else{

                    String UserData = prefs.getString(DATA, "");
                    if (!UserData.isEmpty()) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<ArrayList<User>>() {}.getType();
                        list_user = gson.fromJson(UserData, type);
                    }

                    User user=new User(username,email,passwordConfirmation);
                    list_user.add(user);

                    Gson gson = new Gson();
                    String newUserData = gson.toJson(list_user);

                    editor.putString(DATA, newUserData);
                    editor.apply();

                    startActivity(new Intent(Registration.this, Login.class));

                }


            }
        });
    }



    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
}