package com.example.ass1_duhaidi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity_three extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static final String DATA = "DATA";

    ArrayList<Task> box_task=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        setupSharedPrefs();

        box_task = new ArrayList<>();
        ArrayList<Task> box_task_due = new ArrayList<>();

        ListView lv_DueTask = findViewById(R.id.DueTask);

        String tasksString = prefs.getString("DATA", "");

        if (!tasksString.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Task>>() {
            }.getType();
            box_task = gson.fromJson(tasksString, type);


            for (int i = 0; i < box_task.size(); i++) {
                if (box_task.get(i).getStatus().equals("due")) {
                    box_task_due.add(box_task.get(i));
                }
            }

            ArrayAdapter<Task> listAdapter = new ArrayAdapter<Task>(this,
                    android.R.layout.simple_list_item_1, box_task_due);

            lv_DueTask.setAdapter(listAdapter);

            AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int j, long id) {

                    box_task.get((int)id).setStatus("done");
                    box_task_due.get((int)id).setStatus("done");
                    listAdapter.notifyDataSetChanged();

                    String updateTasksString = gson.toJson(box_task);
                    editor.putString(DATA, updateTasksString);
                    editor.apply();

                    box_task=gson.fromJson(updateTasksString, type);
                    box_task_due.clear();
                    for (int i = 0; i < box_task.size(); i++) {
                        if (box_task.get(i).getStatus().equals("due")) {
                            box_task_due.add(box_task.get(i));
                        }
                    }

                    listAdapter.notifyDataSetChanged();
                }

            };

            lv_DueTask.setOnItemClickListener(itemClickListener);


        }

    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
}