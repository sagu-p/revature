package com.vivek.paradiseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomerProfile extends AppCompatActivity {

    public TextView name1, email1, num1, gen1, bdate1, add1, rp1;
    public String name, email, num, gen, bdate, add, rp, url, show_status, show_message;
    public Customer_POJO module;
    public ArrayList<Customer_POJO> Data_list = new ArrayList<>();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        pref = getApplicationContext().getSharedPreferences(sec.MyPrefrences, Context.MODE_PRIVATE);
        editor = pref.edit();

        Email = pref.getString("Email",null);

        name1 = findViewById(R.id.name11);
        email1 = findViewById(R.id.email11);
        num1 = findViewById(R.id.number);
        gen1 = findViewById(R.id.gender);
        bdate1 = findViewById(R.id.bdate);
        add1 = findViewById(R.id.address);
        rp1 = findViewById(R.id.rp);


        //email="akshit@gmail.com";
        //url=http://localhost/project/customer_profile.php?c_email=w@q.com
        url = "http://" + IP.ip + "/project/customer_profile.php?c_email=" + Email;

        getData();


    }


    public void getData() {

        Log.e("url ", url);

        StringRequest rs = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Toast.makeText(CustomerProfile.this,"Hello",Toast.LENGTH_SHORT).show();
                showjson(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(CustomerProfile.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


        RequestQueue rq = Volley.newRequestQueue(CustomerProfile.this);
        rq.add(rs);


    }

    public void showjson(String response) {

        //JSONObject jsonObject= null;
        try {
            // Toast.makeText(sec.this,"HELOOOOO",Toast.LENGTH_SHORT).show();
            if (response != null) {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray result = jsonObject.getJSONArray("Android");
                for (int i = 0; i <= result.length(); i++) {

                    JSONObject data = result.getJSONObject(i);
                    email = data.getString("Email");
                    name = data.getString("Name");
                    num = data.getString("Number");
                    gen = data.getString("Gender");
                    bdate = data.getString("Bdate");
                    rp = data.getString("Reward_point");
                    add = data.getString("Address");
                    show_status = data.getString("status");
                    show_message = data.getString("message");
                    Log.e("status", show_status);
                    module = new Customer_POJO(show_status, show_message);
                    Data_list.add(module);


                    name1.setText(name);
                    email1.setText(email);
                    num1.setText(num);
                    gen1.setText(gen);
                    bdate1.setText(bdate);
                    add1.setText(add);
                    rp1.setText(rp);

                    Toast.makeText(CustomerProfile.this,show_message,Toast.LENGTH_SHORT).show();
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}