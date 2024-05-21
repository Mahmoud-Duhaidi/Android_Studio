package com.example.birzeit_university_student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity10_payment extends AppCompatActivity {

    private EditText editTextHours;
    private Button calculateButton;
    private TextView resultTextView;
    private TextView thecost;

    private TextView themajor;

    private RequestQueue queue;
    private double majorCostPerHour;
    private double number;
    private double result;

    private String url1, url2;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activityb10_payment);

        queue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        id = (int)intent.getExtras().get("stud_id");

        editTextHours = findViewById(R.id.editTextHours);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        thecost = findViewById(R.id.thecost);
        themajor=findViewById(R.id.themajor);
        url1 = "http://10.0.2.2:5000/student/" + id;

        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, url1,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String name_of_major = response.getString("stud_major");

                    fetchMajorData(name_of_major);

                } catch (JSONException exception) {
                    Log.e("Error", "JSONException: " + exception.getMessage());
                    Toast.makeText(MainActivity10_payment.this,
                            "Error parsing JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity10_payment.this,
                        "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error", "VolleyError: " + error.getMessage());
            }
        });

        queue.add(request1);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    private void fetchMajorData(String majorName) {
        url2 = "http://10.0.2.2:5000/majorcost/" + majorName;

        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url2,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    majorCostPerHour = response.getDouble("major_cost_per_hour");
                    thecost.setText( "Cost of Your Hour-Major is : " + majorCostPerHour);
                    themajor.setText("your Major is : " + majorName);


                } catch (JSONException exception) {
                    Log.e("Error", "JSONException: " + exception.getMessage());
                    Toast.makeText(MainActivity10_payment.this,
                            "Error parsing JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity10_payment.this,
                        "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error", "VolleyError: " + error.getMessage());
            }
        });

        queue.add(request2);
    }

    private void calculateResult() {
        String numberString = editTextHours.getText().toString();
        if (!numberString.isEmpty()) {
            number = Double.parseDouble(numberString);
            result = (majorCostPerHour * number) + 70 ;
            resultTextView.setText("Result: " + result);
        } else {
            Toast.makeText(MainActivity10_payment.this,
                    "Please enter a number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity10_payment.this, MainActivity5_studentHome.class);
        intent.putExtra("stud_id", id);
        startActivity(intent);
        finish();
    }
};
