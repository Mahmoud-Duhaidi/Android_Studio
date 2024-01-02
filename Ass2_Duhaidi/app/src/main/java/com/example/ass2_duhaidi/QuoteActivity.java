package com.example.ass2_duhaidi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class QuoteActivity extends AppCompatActivity {
    private final String url = "https://quotes15.p.rapidapi.com/quotes/random/";
    private final String apiKey = "20df4ae42fmsh2b0269c5e2ecfb3p1df23bjsn637bc1d7071c";
    private TextView txtResult;
    private DecimalFormat df = new DecimalFormat("#.##");
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        txtResult = findViewById(R.id.txtResult);
        queue = Volley.newRequestQueue(this);
    }

    public void btnShow_Click(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //txtResult.setText(response);
                        String result = "";
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String content = jsonObject.getString("content");
                            String originator = jsonObject.getJSONObject("originator").getString("name");
                            result = "Quote: " + content + "\nAuthor: " + originator;

                            txtResult.setText(result);
                            // Close keyboard
                            InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            input.hideSoftInputFromWindow(view.getWindowToken(), 0);

                        } catch (JSONException exception) {
                            exception.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(QuoteActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-RapidAPI-Key", apiKey);
                headers.put("X-RapidAPI-Host", "quotes15.p.rapidapi.com");
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
