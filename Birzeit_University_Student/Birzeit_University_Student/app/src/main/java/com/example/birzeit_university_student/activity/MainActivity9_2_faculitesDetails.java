package com.example.birzeit_university_student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.birzeit_university_student.R;
import com.example.birzeit_university_student.activity_tools.MajorAdapter;
import com.example.birzeit_university_student.classes.Major;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity9_2_faculitesDetails extends AppCompatActivity {
    private List<Major> majors=new ArrayList<>();
    private RecyclerView major_recycler;
    private RequestQueue queue;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity9_2_faculitesdetails);
        Intent intent = getIntent();
        id = (int) intent.getExtras().get("stud_id");
        String stud_fac= intent.getStringExtra("stud_fac");
        queue = Volley.newRequestQueue(this);

        // Initialize views
        major_recycler = findViewById(R.id.major_recycler);

        major_recycler.setLayoutManager(new LinearLayoutManager(this));
        loadMajors(stud_fac);



    }

    private void loadMajors(String stud_fac){
        String url = "http://10.0.2.2:5000/majorimage/"+stud_fac;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        majors.add(new Major(null, 0, obj.getString("major_image")));
                    } catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }

                MajorAdapter adapter = new MajorAdapter(MainActivity9_2_faculitesDetails.this,majors);
                adapter.enableCircularScrolling(true);
                major_recycler.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity9_2_faculitesDetails.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
                Log.d("Error_json", error.toString());
            }
        });

        queue.add(request);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity9_2_faculitesDetails.this, MainActivity9_1_faculites.class);
        intent.putExtra("stud_id", id);
        startActivity(intent);
        finish();
    }
}
