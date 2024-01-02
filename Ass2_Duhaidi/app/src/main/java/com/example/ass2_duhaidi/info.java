package com.example.ass2_duhaidi;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class info extends AppCompatActivity {
    private final String url = "https://omgvamp-hearthstone-v1.p.rapidapi.com/info";
    private final String apiKey = "20df4ae42fmsh2b0269c5e2ecfb3p1df23bjsn637bc1d7071c";
    private TextView txtHearthstoneInfo;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        txtHearthstoneInfo = findViewById(R.id.txtHearthstoneInfo);
        queue = Volley.newRequestQueue(this);
    }

    public void btnGetHearthstoneInfo_Click(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String result = "";
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray classesArray = jsonObject.getJSONArray("classes");
                            JSONArray setsArray = jsonObject.getJSONArray("sets");
                            // Add more arrays as needed

                            result = "Classes: " + classesArray.toString() + "\n"
                                    + "Sets: " + setsArray.toString();
                            // Append more information as needed

                            txtHearthstoneInfo.setText(result);

                        } catch (JSONException exception) {
                            exception.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(info.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-RapidAPI-Key", apiKey);
                headers.put("X-RapidAPI-Host", "omgvamp-hearthstone-v1.p.rapidapi.com");
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}