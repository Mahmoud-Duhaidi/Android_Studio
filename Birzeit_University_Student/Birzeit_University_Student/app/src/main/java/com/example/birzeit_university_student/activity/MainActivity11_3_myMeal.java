package com.example.birzeit_university_student.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.birzeit_university_student.R;
import com.example.birzeit_university_student.activity_tools.mealAdapter;
import com.example.birzeit_university_student.classes.Meal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity11_3_myMeal extends AppCompatActivity {

    private Button buttonFinishAll;
    private TextView textViewTotal;
    private RequestQueue queue;
    int sid=0;
    RecyclerView recycle2;
    private float totalPrice = 0.0F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityb11_3_my_meal);
        queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        sid = (int)intent.getExtras().get("stud_id");

        // Initialize views
        recycle2 = findViewById(R.id.mealsRecyclerView);
        textViewTotal = findViewById(R.id.textViewTotal);
        buttonFinishAll =findViewById(R.id.finishAll);

        getitems();


        buttonFinishAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://10.0.2.2:5000/deleteAllItem/" + sid ;


                // Create a JsonObjectRequest with DELETE method
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE,url,null ,new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                             " you finish this item !!! THANKS "

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VolleyError", error.toString());
                    }
                }
                );

                queue.add(request);

                Activity activity = MainActivity11_3_myMeal.this;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String str1 = "you finish all items !!! THANKS";

                        Toast.makeText(activity, str1,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity, MainActivity11_3_myMeal.class);
                        intent.putExtra("stud_id", sid);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                }, 0);
                //Log.d("dddd" , "deleted successfully");

            }
        });

}

    public void getitems() {

        String url = "http://10.0.2.2:5000/myorder/" + sid;
        ArrayList<Meal> myMealItems=new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Meal myMealItem = new Meal(obj.getString("cafeteriaName"), obj.getInt("productId"), obj.getString("productName"), obj.getString("size"),obj.getDouble("price"), obj.getInt("qun") );
                        myMealItems.add(myMealItem);
                        totalPrice+=obj.getDouble("price") * obj.getInt("qun");

                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }

                recycle2.setLayoutManager(new LinearLayoutManager(MainActivity11_3_myMeal.this));
                mealAdapter listAdapter2 = new mealAdapter(MainActivity11_3_myMeal.this,myMealItems , sid);
                recycle2.setAdapter(listAdapter2);

                textViewTotal.setText(String.valueOf(totalPrice) + " $");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity11_3_myMeal.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
                Log.d("Error_json", error.toString());
            }
        });

        queue.add(request);

    }




    }
