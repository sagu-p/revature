package com.vivek.paradiseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Appointment_detail extends AppCompatActivity {

    public String app_id, service_name1, emp_name1, app_date1, app_time1, status1, url;

    public TextView service_name, emp_name, app_date, app_time, status;

    public Appointment_POJO main_module;
    public ArrayList<Appointment_POJO> Data_list = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_detail);

        Intent i = getIntent();
        app_id = i.getStringExtra("id");

        service_name = findViewById(R.id.service_name);
        emp_name = findViewById(R.id.emp_name);
        app_date = findViewById(R.id.app_date);
        app_time = findViewById(R.id.app_time);
        status = findViewById(R.id.status);

        url = "http://" + IP.ip + "/project_para_salon/appointment_detail.php?app_id=" + app_id;
        getData();

    }

    public void getData() {

        Log.e("url ", url);

        StringRequest rs = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Toast.makeText(ShowAppointment.this,"Hello",Toast.LENGTH_SHORT).show();
                showjson(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        rq.add(rs);


    }

    public void showjson(String response) {

        //JSONObject jsonObject= null;
        try {
            //Toast.makeText(ShowAppointment.this,"HELOOOOO",Toast.LENGTH_SHORT).show();
            if (response != null) {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray result = jsonObject.getJSONArray("Android");
                for (int i = 0; i <= result.length(); i++) {

                    JSONObject data = result.getJSONObject(i);

                    service_name1 = data.getString("service_name");
                    emp_name1 = data.getString("emp_name");
                    app_date1 = data.getString("app_date");
                    app_time1 = data.getString("app_time");
                    status1 = data.getString("app_status");

                    String show_status = data.getString("status");
                    String show_message = data.getString("message");

                    Log.e("status", show_message);

                    main_module = new Appointment_POJO(show_status, show_message);
                    Data_list.add(main_module);

                    service_name.setText(service_name1);
                    emp_name.setText(emp_name1);
                    app_date.setText(app_date1);
                    app_time.setText(app_time1);

                    if (status1.equals("0"))
                    {
                        status.setText("Appointment not Confirmed.");
                    }
                    else if(status1.equals("1"))
                    {
                        status.setText("Apponitment confirmed.");
                    }
                    else if (status1.equals("-1"))
                    {
                        status.setText("Apponitment request declined.");
                    }
                    else
                    {
                        status.setText("Previous Apponitment.");
                    }

                    //Toast.makeText(getApplicationContext(),show_message,Toast.LENGTH_SHORT).show();
                }


            }
        } catch (JSONException e) {

            //String h="shb";
            //Log.e("h",h);
            //Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
