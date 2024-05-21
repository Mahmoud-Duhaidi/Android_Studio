package com.example.birzeit_university_student.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity11_1_cafeteria extends AppCompatActivity {
    private boolean flag = false;
    private Button buttonMyMeal;
    private ListView listViewCafeteria;
    private RequestQueue queue;
    int sid;
    String stud_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityb11_1_cafeteria);

        queue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        sid = (int)intent.getExtras().get("stud_id");
        stud_name = (String) intent.getExtras().get("stud_name");

        // Initialize views
        setupViews();

        fillListCafeteria();


        buttonMyMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity11_1_cafeteria.this,  MainActivity11_3_myMeal.class);
                intent.putExtra("stud_id", sid);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (flag) {
            flag = false;
            Intent intent = new Intent(MainActivity11_1_cafeteria.this, MainActivity5_studentHome.class);
            intent.putExtra("stud_id", sid);
            intent.putExtra("stud_name", stud_name);
            startActivity(intent);
            finish();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        flag=true;
    }


    private void setupViews() {

        buttonMyMeal =findViewById(R.id.button_myMeal);
        listViewCafeteria=findViewById(R.id.listViewCafeteria);

    }


    public void fillListCafeteria(){

        String url = "http://10.0.2.2:5000/cafeterias";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<String> alCafeteria=new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        alCafeteria.add(obj.getString("cafeteriaName"));
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }



                ArrayAdapter<String> listAdapter = new ArrayAdapter<>(MainActivity11_1_cafeteria.this , android.R.layout.simple_list_item_1 , alCafeteria);
                listViewCafeteria.setAdapter(listAdapter);
                AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view,
                                            int position,
                                            long id) {
                        Intent intent = new Intent(MainActivity11_1_cafeteria.this, MainActivity11_2_cafeteriaDetails.class);
                        intent.putExtra("stud_id", sid);
                        intent.putExtra("stud_name", stud_name);
                        intent.putExtra("cafeteriaName", alCafeteria.get((int)id));
                        startActivity(intent);

                    }
                };
                listViewCafeteria.setOnItemClickListener(itemClickListener);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity11_1_cafeteria.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
                Log.d("Error_json", error.toString());
            }


        });
        queue.add(request);

    }

}