package com.example.birzeit_university_student.activity_tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.birzeit_university_student.R;
import com.example.birzeit_university_student.activity.MainActivity11_3_myMeal;
import com.example.birzeit_university_student.classes.Meal;

import org.json.JSONObject;

import java.util.ArrayList;

public class mealAdapter extends RecyclerView.Adapter<mealAdapter.ViewHolder> {


        private RequestQueue queue;
        private Context context;
        ArrayList<Meal> meals;
        int sid=0;
        public mealAdapter(Context context, ArrayList<Meal> meals, int sid) {
            this.context = context;
            this.meals = meals;
            this.sid = sid;
        }

        @Override
        public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){

            CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_meal, parent,false);
            return new ViewHolder(v);

        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position){


            CardView cardview = holder.cardView;

            TextView txt0 = (TextView)cardview.findViewById(R.id.textViewCafeteriaName);
            txt0.setText(meals.get(position).getCafeteriaName());

            TextView txt1 = (TextView)cardview.findViewById(R.id.textViewProductName2);
            txt1.setText(meals.get(position).getProductName());

            TextView txt2 = (TextView)cardview.findViewById(R.id.textViewProductSize2);
            txt2.setText(meals.get(position).getProductSize());

            TextView txt3 = (TextView)cardview.findViewById(R.id.textViewProductPrice2);
            txt3.setText( meals.get(position).getProductPrice()+"");

            TextView txt4 = (TextView)cardview.findViewById(R.id.textViewProductqun);
            txt4.setText( meals.get(position).getQuantity()+"");

            int pid = meals.get(position).getProductId();


            cardview.findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    String url = "http://10.0.2.2:5000/deleteItem/" + sid +"/" + pid ;
                      queue = Volley.newRequestQueue(context);


                    // Create a JsonObjectRequest with DELETE method
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE,url,null ,new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
//                            String str1 = "you finish this item !!! THANKS";
//                            Log.d("hhhh" ,"str1");

                        }
                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d("VolleyError", error.toString());
                                }
                            }
                    );

                    queue.add(request);
                    Activity activity = (Activity) context;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    String str1 = "you finish this item !!! THANKS";

                                    Toast.makeText(context, str1,Toast.LENGTH_SHORT).show();
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

        @Override
        public int getItemCount(){return  meals.size();}

        public  static class ViewHolder extends RecyclerView.ViewHolder{
            private CardView cardView;
            public ViewHolder(CardView cardView){
                super(cardView);
                this.cardView = cardView;
            }


        }

    }





