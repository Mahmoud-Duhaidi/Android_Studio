package com.example.birzeit_university_student.activity_tools;

import android.content.Context;
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
import com.example.birzeit_university_student.classes.Product;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ViewHolder> {
    private Context context;
    ArrayList<Product> products;
    int sid=0;
    public productAdapter(Context context, ArrayList<Product> products, int sid) {
        this.context = context;
        this.products = products;
        this.sid = sid;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
       CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product, parent,false);
       return new ViewHolder(v);

    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        CardView cardview = holder.cardView;

        TextView txt1 = (TextView)cardview.findViewById(R.id.textViewProductName);
        txt1.setText(products.get(position).getProductName());

        TextView txt2 = (TextView)cardview.findViewById(R.id.textViewProductSize);
        txt2.setText(products.get(position).getProductSize());

        TextView txt3 = (TextView)cardview.findViewById(R.id.textViewProductPrice);
        txt3.setText( products.get(position).getProductPrice()+"");

        TextView txt4 = (TextView)cardview.findViewById(R.id.textViewProductQ);
        txt4.setText( products.get(position).getQuantity()+"");

        int pos = position;

        cardview.findViewById(R.id.btnDec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(products.get(pos).getQuantity() ==1){
                    return;
                }
                products.get(pos).setQuantity( products.get(pos).getQuantity()-1);

                txt4.setText( products.get(pos).getQuantity()+"");
            }
        });

        cardview.findViewById(R.id.btnInec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                products.get(pos).setQuantity( products.get(pos).getQuantity()+1);

                txt4.setText( products.get(pos).getQuantity()+"");
            }
        });

        int pid = products.get(position).getProductId();

        cardview.findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = "http://10.0.2.2:5000/addItem" ;

                RequestQueue queue = Volley.newRequestQueue(context);

                JSONObject jsonParams = new JSONObject();
                try {
                    jsonParams.put("stud_id", sid);
                    jsonParams.put("productId", pid);
                    jsonParams.put("qun", products.get(pos).getQuantity());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Create a JsonObjectRequest with POST method
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,jsonParams,new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String str = "Item Added successfully";

                        Toast.makeText(context, str,Toast.LENGTH_SHORT).show();
                        //                String str = "Item Added successfully";
                        //
                        //                Log.d("Hint", str);

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
               // Log.d("dddd" , "added successfully");

            }
        });
    }

    @Override
    public int getItemCount(){return  products.size();}

     public  static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }

     }

}
