package com.example.birzeit_university_student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.example.birzeit_university_student.activity_tools.productAdapter;
import com.example.birzeit_university_student.classes.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity11_2_cafeteriaDetails extends AppCompatActivity {

    private Button buttonMyMeal;
    private Button buttonSearch;
    private TextView textViewHint;
    private Spinner ChooseCategorySpinner;
    private RequestQueue queue;
    ArrayList<String> categories=new ArrayList<>();

    int sid=0;
    String cafeteriaName ="";
    String categoryN = "";
    RecyclerView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityb11_2_cafeteria_details);

        queue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        sid = (int)intent.getExtras().get("stud_id");
        String stud_name = (String) intent.getExtras().get("stud_name");
        cafeteriaName = (String) intent.getExtras().get("cafeteriaName");

        // Initialize views
        setupViews();

        textViewHint.setText(cafeteriaName);

        fillSpinnerCategory();

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItems();
            }
        });




        buttonMyMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity11_2_cafeteriaDetails.this,  MainActivity11_3_myMeal.class);
                intent.putExtra("stud_id", sid);
                startActivity(intent);
            }
        });


    }


    private void setupViews() {

        buttonMyMeal = findViewById(R.id.button_myMeal);
        textViewHint = findViewById(R.id.textViewHint);
        buttonSearch = findViewById(R.id.btnSearch);
        ChooseCategorySpinner = findViewById(R.id.ChooseCategorySpinner);
        recycle = findViewById(R.id.productRecyclerView);

    }



    public void fillSpinnerCategory(){


        String url = "http://10.0.2.2:5000/category/" + cafeteriaName;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        categories.add(obj.getString("category"));
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }

                ArrayAdapter<String> spinner_category_adapter = new ArrayAdapter<>(MainActivity11_2_cafeteriaDetails.this, android.R.layout.simple_spinner_item, categories);
                spinner_category_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ChooseCategorySpinner.setAdapter(spinner_category_adapter);

                ChooseCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        categoryN = parent.getItemAtPosition(position).toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity11_2_cafeteriaDetails.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
                Log.d("Error_json", error.toString());
            }
        });
        queue.add(request);
    }




    public void getItems(){

        String url = "http://10.0.2.2:5000/products/" + cafeteriaName + "/" + categoryN;

        ArrayList<Product> items=new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Product item = new Product(obj.getInt("productId"), obj.getString("productName"), obj.getString("size"),obj.getDouble("price"),1 );
                        items.add(item);
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }

                recycle.setLayoutManager(new LinearLayoutManager(MainActivity11_2_cafeteriaDetails.this));
                productAdapter listAdapter = new productAdapter(MainActivity11_2_cafeteriaDetails.this,items, sid);
                recycle.setAdapter(listAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity11_2_cafeteriaDetails.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
                Log.d("Error_json", error.toString());
            }
        });

        queue.add(request);
    }

}