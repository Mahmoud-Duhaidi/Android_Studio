package com.example.birzeit_university_student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.birzeit_university_student.R;
import com.example.birzeit_university_student.activity_tools.TaskAdapter;
import com.example.birzeit_university_student.classes.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity12_3_doneTask extends AppCompatActivity {
    private ListView listViewDoneTask;

    private RequestQueue queue;

    private int stud_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityb12_3_donetask);
        queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        stud_id = (int)intent.getExtras().get("stud_id");

        listViewDoneTask = findViewById(R.id.listViewDoneTask);

        String url = "http://10.0.2.2:5000/donetask/"+stud_id;
        ArrayList<Task> al_task=new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Task task=new Task(obj.getInt("task_id"), obj.getString("task_title"), obj.getString("task_description"), obj.getString("task_date"), obj.getString("task_time"), obj.getString("task_status"));
                        al_task.add(task);
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }

                TaskAdapter listAdapter = new TaskAdapter(MainActivity12_3_doneTask.this, al_task);
                listViewDoneTask.setAdapter(listAdapter);

                AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view,
                                            int position,
                                            long id) {
                        Intent intent = new Intent(MainActivity12_3_doneTask.this, MainActivity12_5_DoneTaskDetails.class);
                        intent.putExtra("stud_id", stud_id);
                        intent.putExtra("task_id", al_task.get((int)id).getId());
                        startActivity(intent);

                    }
                };
                listViewDoneTask.setOnItemClickListener(itemClickListener);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity12_3_doneTask.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
                Log.d("Error_json", error.toString());
            }
        });

        queue.add(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity12_3_doneTask.this, MainActivity12_1_taskHome.class);
        intent.putExtra("stud_id", stud_id
        );
        startActivity(intent);
        finish();
    }
}
