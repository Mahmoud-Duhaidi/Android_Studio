package com.example.ass1_duhaidi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity_four extends AppCompatActivity {

    SharedPreferences prefs;
    public static final String DATA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        ArrayList<Task> box_task = new ArrayList<>();
        ArrayList<Task> box_task_done = new ArrayList<>();

        ListView lv_DoneTask = findViewById(R.id.DoneTask);

        String tasksString = prefs.getString("DATA", "");

        if (!tasksString.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Task>>() {
            }.getType();
            box_task = gson.fromJson(tasksString, type);


            for (int i = 0; i < box_task.size(); i++) {
                if (box_task.get(i).getStatus().equals("done")) {
                    box_task_done.add(box_task.get(i));
                }
            }

            ArrayAdapter<Task> listAdapter = new ArrayAdapter<Task>(this,
                    android.R.layout.simple_list_item_1, box_task_done);

            lv_DoneTask.setAdapter(listAdapter);
        }

    }
}
