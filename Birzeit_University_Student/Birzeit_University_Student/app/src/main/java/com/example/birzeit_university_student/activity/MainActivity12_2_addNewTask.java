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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.birzeit_university_student.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity12_2_addNewTask extends AppCompatActivity {
    private TextView textViewErrorMessage;

    private EditText editTextTaskTitle;

    private EditText editTextTaskDescription;

    private Spinner spinnerYear;
    private String selectedYear;
    private Spinner spinnerMonth;
    private String selectedMonth;
    private Spinner spinnerDay;
    private String selectedDay;
    private Spinner spinnerHours;
    private String selectedHours;
    private Spinner spinnerMinutes;
    private String selectedMinutes;
    private Spinner spinnerDayNight;
    private String selectedDayNight;

    private Button buttonAddTask;

    int stud_id;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityb12_2_addnewtask);
        Intent intent = getIntent();
        stud_id = (int)intent.getExtras().get("stud_id");
        queue = Volley.newRequestQueue(this);

        // Initialize views
        textViewErrorMessage=findViewById(R.id.textViewErrorMessage);
        textViewErrorMessage.setText("Please fill all the data !!!");
        textViewErrorMessage.setVisibility(View.INVISIBLE);

        editTextTaskTitle=findViewById(R.id.editTextTaskTitle);

        editTextTaskDescription=findViewById(R.id.editTextTaskDescription);

        spinnerYear=findViewById(R.id.spinnerYear);
        String years[]={"yyyy","2024","2025","2026","2027","2028","2029","2030","2030"};
        ArrayAdapter<String> spinner_year_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        spinner_year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(spinner_year_adapter);
        spinnerYear.setSelection(0);
        selectedYear="";
        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedYear = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerMonth=findViewById(R.id.spinnerMonth);
        String months[]={"m","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        ArrayAdapter<String> spinner_month_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, months);
        spinner_month_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(spinner_month_adapter);
        spinnerMonth.setSelection(0);
        selectedMonth="";
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMonth = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerDay=findViewById(R.id.spinnerDay);
        String days[]={"dd","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        ArrayAdapter<String> spinner_day_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        spinner_day_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDay.setAdapter(spinner_day_adapter);
        spinnerDay.setSelection(0);
        selectedDay="";
        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDay = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerHours=findViewById(R.id.spinnerHours);
        String hours[]={"hh","01","02","03","04","05","06","07","08","09","10","11","12"};
        ArrayAdapter<String> spinner_hour_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hours);
        spinner_hour_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHours.setAdapter(spinner_hour_adapter);
        spinnerHours.setSelection(0);
        selectedHours="";
        spinnerHours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedHours = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerMinutes=findViewById(R.id.spinnerMinutes);
        String minutes[]=new String[62];
        minutes[0]="mm";
        minutes[1]="00";
        minutes[2]="01";
        minutes[3]="02";
        minutes[4]="03";
        minutes[5]="04";
        minutes[6]="05";
        minutes[7]="06";
        minutes[8]="07";
        minutes[9]="08";
        minutes[10]="09";
        for(int i=11;i<=61;i++){
            minutes[i]=(i-1)+"";
        }
        ArrayAdapter<String> spinner_minute_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, minutes);
        spinner_minute_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMinutes.setAdapter(spinner_minute_adapter);
        spinnerMinutes.setSelection(0);
        selectedMinutes="";
        spinnerMinutes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMinutes = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerDayNight=findViewById(R.id.spinnerDayNight);
        String dayNight[]={"AM","PM"};
        ArrayAdapter<String> spinner_dayNight_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dayNight);
        spinner_dayNight_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDayNight.setAdapter(spinner_dayNight_adapter);
        spinnerDayNight.setSelection(0);
        selectedDayNight="";
        spinnerDayNight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDayNight = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonAddTask=findViewById(R.id.buttonAddTask);

        //************* action

        // AddTask button
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewErrorMessage.setVisibility(View.GONE);
                if(editTextTaskTitle.getText().toString().equals(null) || editTextTaskDescription.getText().toString().equals(null) ||
                        (selectedYear.equals("") || selectedYear.equals("yyyy")) || (selectedMonth.equals("") || selectedMonth.equals("m")) ||
                        (selectedDay.equals("") || selectedDay.equals("dd")) || (selectedHours.equals("") || selectedHours.equals("hh")) ||
                        (selectedMinutes.equals("") || selectedMinutes.equals("mm"))){
                    textViewErrorMessage.setVisibility(View.VISIBLE);
                }
                else{

                    // add new task
                    String task_date=selectedYear+" "+"/"+" "+selectedMonth+" "+"/"+" "+selectedDay;
                    String task_time=selectedHours+":"+selectedMinutes+"  "+selectedDayNight;
                    addTask(editTextTaskTitle.getText().toString(),editTextTaskDescription.getText().toString(),task_date,task_time,"Due",stud_id);
                    editTextTaskTitle.getText().clear();
                    editTextTaskDescription.getText().clear();
                    spinnerYear.setSelection(0);
                    selectedYear="";
                    spinnerMonth.setSelection(0);
                    selectedMonth="";
                    spinnerDay.setSelection(0);
                    selectedDay="";
                    spinnerHours.setSelection(0);
                    selectedHours="";
                    spinnerMinutes.setSelection(0);
                    selectedMinutes="";
                    spinnerDayNight.setSelection(0);
                    selectedDayNight="";

                }

            }
        });
    }

    private void addTask(String task_title, String task_description, String task_date, String task_time, String task_status, int stud_id) {
        String url = "http://10.0.2.2:5000/addTask";

        RequestQueue queue = Volley.newRequestQueue(MainActivity12_2_addNewTask.this);

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("task_title", task_title);
            jsonParams.put("task_description", task_description);
            jsonParams.put("task_date", task_date);
            jsonParams.put("task_time", task_time);
            jsonParams.put("task_status", task_status);
            jsonParams.put("stud_id", stud_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create a JsonObjectRequest with POST method
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,jsonParams,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String str = "Task Added successfully";

                Toast.makeText(MainActivity12_2_addNewTask.this, str,Toast.LENGTH_SHORT).show();

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
        Intent intent = new Intent(MainActivity12_2_addNewTask.this, MainActivity12_1_taskHome.class);
        intent.putExtra("stud_id", stud_id);
        startActivity(intent);
        finish();
    }
}
