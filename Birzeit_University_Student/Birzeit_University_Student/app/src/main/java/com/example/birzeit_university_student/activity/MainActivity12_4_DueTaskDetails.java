package com.example.birzeit_university_student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class MainActivity12_4_DueTaskDetails extends AppCompatActivity {
    private TextView v_TaskTitle;
    private TextView v_TaskDescription;
    private TextView v_TaskStatus;
    private TextView v_TaskDate;
    private TextView v_TaskTime;

    private Button buttonCompleteTask;

    private Button buttonDeleteTask;

    private RequestQueue queue;

    private Task task;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityb12_4_duetaskdetails);

        queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        id = (int)intent.getExtras().get("stud_id");
        int task_id=(int)intent.getExtras().get("task_id");

        v_TaskTitle=findViewById(R.id.v_TaskTitle);
        v_TaskDescription=findViewById(R.id.v_TaskDescription);
        v_TaskStatus=findViewById(R.id.v_TaskStatus);
        v_TaskDate=findViewById(R.id.v_TaskDate);
        v_TaskTime=findViewById(R.id.v_TaskTime);

        buttonCompleteTask=findViewById(R.id.buttonCompleteTask);
        buttonDeleteTask=findViewById(R.id.buttonDeleteTask);

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
                Toast.makeText(MainActivity12_4_DueTaskDetails.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
                Log.d("Error_json", error.toString());
            }
        });

        queue.add(request);


        buttonCompleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTask(id,task_id,"Done");
                Intent intent = new Intent(MainActivity12_4_DueTaskDetails.this, MainActivity12_1_taskHome.class);
                intent.putExtra("stud_id", id);
                startActivity(intent);
                finish();
            }
        });

        buttonDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask(id,task_id);
            }
        });

    }

    private void updateTask(int stud_id, int task_id, String task_status) {
        String updateUrl = "http://10.0.2.2:5000/update_task/"+stud_id+"/"+task_id;

        JSONObject updateData = new JSONObject();
        try {
            updateData.put("task_status", task_status);
            v_TaskStatus.setText(task_status);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest updateRequest = new JsonObjectRequest(Request.Method.PUT, updateUrl, updateData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity12_4_DueTaskDetails.this, "Student updated successfully",
                                Toast.LENGTH_SHORT).show();
                        // Handle any UI updates or additional logic after the update
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity12_4_DueTaskDetails.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
                Log.d("Error_json", error.toString());
            }
        });

        queue.add(updateRequest);
    }

    private void deleteTask(int stud_id, int task_id){
        String deleteUrl = "http://10.0.2.2:5000/deleteTask/" + stud_id+"/"+task_id;

        JsonObjectRequest deleteRequest = new JsonObjectRequest(Request.Method.DELETE, deleteUrl,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(MainActivity12_4_DueTaskDetails.this, "Task deleted successfully",
                        Toast.LENGTH_SHORT).show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity12_4_DueTaskDetails.this, MainActivity12_1_taskHome.class);
                        intent.putExtra("stud_id", id);
                        startActivity(intent);
                        finish();
                    }
                }, 2600);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity12_4_DueTaskDetails.this, "Task deleted successfully",
                        Toast.LENGTH_SHORT).show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity12_4_DueTaskDetails.this, MainActivity12_1_taskHome.class);
                        intent.putExtra("stud_id", id);
                        startActivity(intent);
                        finish();
                    }
                }, 2600);
            }
        });

        queue.add(deleteRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity12_4_DueTaskDetails.this, MainActivity12_1_taskHome.class);
        intent.putExtra("stud_id", id);
        startActivity(intent);
        finish();
    }
}
