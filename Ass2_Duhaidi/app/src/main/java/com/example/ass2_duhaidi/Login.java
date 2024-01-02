package com.example.ass2_duhaidi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Login extends AppCompatActivity {

    ArrayList<User> list_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username1=findViewById(R.id.username1);
        EditText userpassword=findViewById(R.id.userpassword);
        CheckBox checkb=findViewById(R.id.checkb);
        Button btnLogin=findViewById(R.id.btnLogin);
        TextView text2=findViewById(R.id.text2);

        list_user=new ArrayList<>();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String userData = prefs.getString("DATA", "");

        if (!userData.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<User>>() {
            }.getType();
            list_user = gson.fromJson(userData, type);
        }


        String PREF_NAME = "MyPrefs";
        String KEY_USERNAME = "username";
        String KEY_PASSWORD = "password";
        String KEY_REMEMBER = "remember";

        SharedPreferences checkBox_prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        if (checkBox_prefs.getBoolean(KEY_REMEMBER, false)) {
            username1.setText(checkBox_prefs.getString(KEY_USERNAME, ""));
            userpassword.setText(checkBox_prefs.getString(KEY_PASSWORD, ""));
            checkb.setChecked(true);
        }






        // on action

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registration.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredName = username1.getText().toString().trim();
                String enteredPassword = userpassword.getText().toString().trim();

                boolean valid_user=false;
                if (enteredName.isEmpty() || enteredPassword.isEmpty()) {
                    Toast.makeText(Login.this, "Username and password are required", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    for (int i=0;i<list_user.size();i++){
                        if(list_user.get(i).getName().equals(enteredName) && list_user.get(i).getPassword().equals(enteredPassword)){
                            valid_user=true;
                        }
                    }

                    if(valid_user==false){
                        // text error invalid user
                    }
                    else{
                        if (checkb.isChecked()) {
                            SharedPreferences.Editor editor = checkBox_prefs.edit();
                            editor.putString(KEY_USERNAME, enteredName);
                            editor.putString(KEY_PASSWORD, enteredPassword);
                            editor.putBoolean(KEY_REMEMBER, true);
                            editor.apply();
                        } else {
                            SharedPreferences.Editor editor = checkBox_prefs.edit();
                            editor.clear();
                            editor.apply();
                        }

                        startActivity(new Intent(Login.this, HomePage.class));

                    }


                }
            }
        });


    }
}
