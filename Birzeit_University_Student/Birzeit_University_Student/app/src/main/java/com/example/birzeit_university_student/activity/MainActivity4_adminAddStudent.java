package com.example.birzeit_university_student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.birzeit_university_student.R;
import com.example.birzeit_university_student.activity_tools.StudentAdapter;
import com.example.birzeit_university_student.classes.Major;
import com.example.birzeit_university_student.classes.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity4_adminAddStudent extends AppCompatActivity {
    private TextView textViewErrorMessage;
    private EditText editTextStudentName;
    private Spinner spinnerMajor;
    private String selectedMajor;
    private Button buttonAddStudent;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_adminaddstudent);
        queue = Volley.newRequestQueue(this);

        // Initialize views
        textViewErrorMessage=findViewById(R.id.textViewErrorMessage);
        textViewErrorMessage.setVisibility(View.INVISIBLE);

        editTextStudentName=findViewById(R.id.editTextStudentName);

        spinnerMajor=findViewById(R.id.spinnerMajor);
        String url = "http://10.0.2.2:5000/major";
        ArrayList<String> al_major=new ArrayList<>();
        al_major.add("Select Major");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        al_major.add(obj.getString("major_name"));
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity4_adminAddStudent.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
                Log.d("Error_json", error.toString());
            }
        });

        queue.add(request);

        ArrayAdapter<String> spinner_major_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, al_major);
        spinner_major_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMajor.setAdapter(spinner_major_adapter);
        spinnerMajor.setSelection(0);
        selectedMajor="";
        spinnerMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMajor = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonAddStudent=findViewById(R.id.buttonAddStudent);

        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stud_name=editTextStudentName.getText().toString();

                if(stud_name.equals("") || selectedMajor.equals("Select Major")){
                    textViewErrorMessage.setVisibility(View.VISIBLE);
                }
                else{
                    textViewErrorMessage.setVisibility(View.INVISIBLE);

                    addStudent(stud_name, selectedMajor);
                    editTextStudentName.getText().clear();
                    spinnerMajor.setSelection(0);
                    selectedMajor="";

                }
            }
        });



    }

    private void addStudent(String stud_name, String selectedMajor) {
        String url = "http://10.0.2.2:5000/addStudent";

        RequestQueue queue = Volley.newRequestQueue(MainActivity4_adminAddStudent.this);

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("stud_name", stud_name);
            jsonParams.put("stud_email", stud_name+"@student.birzeit.edu");
            jsonParams.put("stud_password", "123456");
            jsonParams.put("stud_major", selectedMajor);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create a JsonObjectRequest with POST method
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,jsonParams,new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String str = "Student Added successfully";

                    Toast.makeText(MainActivity4_adminAddStudent.this, str,Toast.LENGTH_SHORT).show();

                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("VolleyError", error.toString());
                }
            }
        );

        queue.add(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity4_adminAddStudent.this, MainActivity2_adminHome.class);
        startActivity(intent);
        finish();
    }
}
