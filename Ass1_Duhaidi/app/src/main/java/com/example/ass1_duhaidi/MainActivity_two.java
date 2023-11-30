package com.example.ass1_duhaidi;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity_two extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static final String DATA = "DATA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        setupSharedPrefs();

        EditText name=findViewById(R.id.editTextName);
        EditText content=findViewById(R.id.editTextContent);
        TextView datetext = findViewById(R.id.text_view_date);
        datetext.setText(currentDate);
        datetext.setVisibility(View.INVISIBLE);
        Button add=findViewById(R.id.Add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Task> box_task=new ArrayList<>();

                String load_tasksString = prefs.getString(DATA, "");
                if (!load_tasksString.isEmpty()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<Task>>() {}.getType();
                    box_task = gson.fromJson(load_tasksString, type);
                }


                Task task=new Task(name.getText().toString(),content.getText().toString(),"due",datetext.getText().toString());
                box_task.add(task);

                Gson gson = new Gson();
                String tasksString = gson.toJson(box_task);

                editor.putString(DATA, tasksString);
                editor.commit();


                name.setText("");
                content.setText("");
            }
        });
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
}
