package com.example.birzeit_university_student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.birzeit_university_student.classes.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity3_adminStudentDetails extends AppCompatActivity {

    private TextView textViewStud_Id;
    private TextView textViewStud_name;
    private EditText editTextStud_name;
    private TextView textViewStud_email;
    private EditText editTextStud_email;
    private TextView textViewStud_password;
    private EditText editTextStud_password;
    private TextView textViewStud_major;
    private Spinner EditSpinnerStud_major;
    private String selectedMajor;

    ArrayList<String> al_major=new ArrayList<>();
    private Button buttonEdit,buttonDelete,buttonConfirmEdits,buttonCancelEdits;

    Student student;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_adminstudentdetails);
        queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        int id = (int)intent.getExtras().get("stud_id");

        // Initialize views
        textViewStud_Id=findViewById(R.id.textViewStud_Id);

        textViewStud_name=findViewById(R.id.textViewStud_name);
        editTextStud_name=findViewById(R.id.editTextStud_name);
        editTextStud_name.setVisibility(View.INVISIBLE);

        textViewStud_email=findViewById(R.id.textViewStud_email);
        editTextStud_email=findViewById(R.id.editTextStud_email);
        editTextStud_email.setVisibility(View.INVISIBLE);

        textViewStud_password=findViewById(R.id.textViewStud_password);
        editTextStud_password=findViewById(R.id.editTextStud_password);
        editTextStud_password.setVisibility(View.INVISIBLE);

        textViewStud_major=findViewById(R.id.textViewStud_major);
        EditSpinnerStud_major=findViewById(R.id.EditSpinnerStud_major);
        EditSpinnerStud_major.setVisibility(View.INVISIBLE);
        getStudInfo(id);
        fillSpinnerMajor();


        buttonEdit=findViewById(R.id.buttonEdit);
        buttonDelete=findViewById(R.id.buttonDelete);
        buttonConfirmEdits=findViewById(R.id.buttonConfirmEdits);
        buttonConfirmEdits.setVisibility(View.INVISIBLE);
        buttonCancelEdits=findViewById(R.id.buttonCancelEdits);
        buttonCancelEdits.setVisibility(View.INVISIBLE);



        // Set on Action

        // delete student
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleteUrl = "http://10.0.2.2:5000/deleteStudent/" + id;

                JsonObjectRequest deleteRequest = new JsonObjectRequest(Request.Method.DELETE, deleteUrl,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(MainActivity3_adminStudentDetails.this, "Student deleted successfully",
                                Toast.LENGTH_SHORT).show();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity3_adminStudentDetails.this, MainActivity2_adminHome.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 2600);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity3_adminStudentDetails.this, "Student deleted successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(deleteRequest);
            }
        });

        // edit student
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonDelete.setVisibility(View.GONE);
                buttonEdit.setVisibility(View.GONE);

                textViewStud_name.setVisibility(View.GONE);
                editTextStud_name.setVisibility(View.VISIBLE);
                editTextStud_name.setText(student.getStud_name());

                textViewStud_email.setVisibility(View.GONE);
                editTextStud_email.setVisibility(View.VISIBLE);
                editTextStud_email.setText(student.getStud_email());

                textViewStud_password.setVisibility(View.GONE);
                editTextStud_password.setVisibility(View.VISIBLE);
                editTextStud_password.setText(student.getStud_password());

                textViewStud_major.setVisibility(View.GONE);
                EditSpinnerStud_major.setVisibility(View.VISIBLE);
                for (int i = 0; i < al_major.size(); i++) {
                    if (EditSpinnerStud_major.getItemAtPosition(i).equals(student.getStud_major())) {
                        EditSpinnerStud_major.setSelection(i);
                        break;
                    }
                }

                EditSpinnerStud_major.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (parent.getItemAtPosition(position) != null) {
                            selectedMajor = parent.getItemAtPosition(position).toString();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        selectedMajor=student.getStud_major();
                    }
                });


                buttonConfirmEdits.setVisibility(View.VISIBLE);
                buttonCancelEdits.setVisibility(View.VISIBLE);


            }
        });

        // Confirm Edits
        buttonConfirmEdits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String studName = editTextStud_name.getText().toString();
                String studEmail = editTextStud_email.getText().toString();
                String studPassword = editTextStud_password.getText().toString();


                updateStudent(id,studName,studEmail,studPassword,selectedMajor);

                buttonConfirmEdits.setVisibility(View.GONE);
                buttonCancelEdits.setVisibility(View.GONE);

                textViewStud_name.setVisibility(View.VISIBLE);
                editTextStud_name.setVisibility(View.GONE);

                textViewStud_email.setVisibility(View.VISIBLE);
                editTextStud_email.setVisibility(View.GONE);

                textViewStud_password.setVisibility(View.VISIBLE);
                editTextStud_password.setVisibility(View.GONE);

                textViewStud_major.setVisibility(View.VISIBLE);
                EditSpinnerStud_major.setVisibility(View.GONE);

                buttonDelete.setVisibility(View.VISIBLE);
                buttonEdit.setVisibility(View.VISIBLE);
            }
        });

        // Cancel Edits
        buttonCancelEdits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonConfirmEdits.setVisibility(View.GONE);
                buttonCancelEdits.setVisibility(View.GONE);

                textViewStud_name.setVisibility(View.VISIBLE);
                editTextStud_name.setVisibility(View.GONE);

                textViewStud_email.setVisibility(View.VISIBLE);
                editTextStud_email.setVisibility(View.GONE);

                textViewStud_password.setVisibility(View.VISIBLE);
                editTextStud_password.setVisibility(View.GONE);

                textViewStud_major.setVisibility(View.VISIBLE);
                EditSpinnerStud_major.setVisibility(View.GONE);

                buttonDelete.setVisibility(View.VISIBLE);
                buttonEdit.setVisibility(View.VISIBLE);

            }
        });

    }

    public void getStudInfo(int id){
        String url = "http://10.0.2.2:5000/student/" + id;


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int studId = response.getInt("stud_id");
                    String studName = response.getString("stud_name");
                    String studEmail = response.getString("stud_email");
                    String studPassword = response.getString("stud_password");
                    String studMajor = response.getString("stud_major");

                    student = new Student(studId, studName, studEmail, studPassword, studMajor);
                    textViewStud_Id.setText(String.valueOf(student.getStud_id()));
                    textViewStud_name.setText(student.getStud_name());
                    textViewStud_email.setText(student.getStud_email());
                    textViewStud_password.setText(student.getStud_password());

                    textViewStud_major.setText(student.getStud_major());


                } catch (JSONException exception) {
                    Log.d("Error", exception.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3_adminStudentDetails.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
                Log.d("Error_json", error.toString());
            }
        });

        queue.add(request);
    }

    private void updateStudent(int id, String newName, String newEmail, String newPassword, String newMajor) {
        String updateUrl = "http://10.0.2.2:5000/updateStudent/" + id;

        JSONObject updateData = new JSONObject();
        try {
            updateData.put("stud_name", newName);
            updateData.put("stud_email", newEmail);
            updateData.put("stud_password", newPassword);
            updateData.put("stud_major", newMajor);
            textViewStud_name.setText(newName);
            textViewStud_email.setText(newEmail);
            textViewStud_password.setText(newPassword);
            textViewStud_major.setText(newMajor);
            student=new Student(id,newName,newEmail,newPassword,newMajor);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest updateRequest = new JsonObjectRequest(Request.Method.PUT, updateUrl, updateData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String responses="Student updated successfully";
                        Toast.makeText(MainActivity3_adminStudentDetails.this, responses,
                                Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String responses="Student updated successfully";
                Toast.makeText(MainActivity3_adminStudentDetails.this, responses,
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(updateRequest);
    }

    public void fillSpinnerMajor(){
        String url = "http://10.0.2.2:5000/major";

        al_major.add("Select Major");
        JsonArrayRequest request_major = new JsonArrayRequest(Request.Method.GET, url,
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

                ArrayAdapter<String> spinner_major_adapter = new ArrayAdapter<>(MainActivity3_adminStudentDetails.this, android.R.layout.simple_spinner_item, al_major);
                spinner_major_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                EditSpinnerStud_major.setAdapter(spinner_major_adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity3_adminStudentDetails.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
                Log.d("Error_json", error.toString());
            }
        });
        queue.add(request_major);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity3_adminStudentDetails.this, MainActivity2_adminHome.class);
        startActivity(intent);
        finish();
    }
}
