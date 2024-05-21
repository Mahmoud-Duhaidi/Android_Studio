package com.example.birzeit_university_student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.birzeit_university_student.R;
import com.example.birzeit_university_student.classes.Task;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity12_5_DoneTaskDetails extends AppCompatActivity {
    private TextView v_TaskTitle;
    private TextView v_TaskDescription;
    private TextView v_TaskStatus;
    private TextView v_TaskDate;
    private TextView v_TaskTime;

    private RequestQueue queue;

    private Task task;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityb12_5_donetaskdetails);

        queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        id = (int)intent.getExtras().get("stud_id");
        int task_id=(int)intent.getExtras().get("task_id");

        v_TaskTitle=findViewById(R.id.v_TaskTitle);
        v_TaskDescription=findViewById(R.id.v_TaskDescription);
        v_TaskStatus=findViewById(R.id.v_TaskStatus);
        v_TaskDate=findViewById(R.id.v_TaskDate);
        v_TaskTime=findViewById(R.id.v_TaskTime);


        String url = "http://10.0.2.2:5000/task/"+id+"/"+task_id;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    task=new Task(response.getInt("task_id"),response.getString("task_title"),response.getString("task_description"),response.getString("task_date"),response.getString("task_time"),response.getString("task_status"));
                    v_TaskTitle.setText(task.getTitle());
                    v_TaskDescription.setText(task.getDescription());
                    v_TaskStatus.setText(task.getStatus());
                    v_TaskDate.setText(task.getDate());
                    v_TaskTime.setText(task.getTime());

                } catch (JSONException exception) {
                    Log.d("Error", exception.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity12_5_DoneTaskDetails.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
                Log.d("Error_json", error.toString());
            }
        });

        queue.add(request);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity12_5_DoneTaskDetails.this, MainActivity12_3_doneTask.class);
        intent.putExtra("stud_id", id);
        startActivity(intent);
        finish();
    }
}
