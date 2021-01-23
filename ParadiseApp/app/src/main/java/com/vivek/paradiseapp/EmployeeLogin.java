package com.vivek.paradiseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class EmployeeLogin extends AppCompatActivity {

   public Employee_Registration_POJO main_module;
    public ArrayList<Employee_Registration_POJO> Data_list = new ArrayList<>();

    public TextView t1;
    public Button b1;
    public EditText email1,pass1;

    public String show_email=null;
    public String show_pass=null;
    public String show_status=null;
    public String show_message=null;

    public String email,pass;

    public String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);

        b1=findViewById(R.id.log_button);

        email1=findViewById(R.id.email);
        pass1=findViewById(R.id.pass);

        t1=findViewById(R.id.textView);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmployeeLogin.this,EmployeeRegetrationActivity.class));
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             email=email1.getText().toString();
             pass=pass1.getText().toString();

            // url=http://localhost/project/emp_login.php?emp_email=nadim@gmail.com&&emp_pass=nadim123

                url="http://"+IP.ip+"/project/emp_login.php?emp_email="+email+"&&emp_pass="+pass;

                getData();

            }
        });
    }

    public void getData() {

        Log.e("url ", url);

        StringRequest rs=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showjson(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(EmployeeLogin.this,error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });


        RequestQueue rq= Volley.newRequestQueue(EmployeeLogin.this);
        rq.add(rs);

    }

    public void showjson(String response) {

        //JSONObject jsonObject= null;
        try {
            // Toast.makeText(sec.this,"HELOOOOO",Toast.LENGTH_SHORT).show();
            if (response != null) {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray result=jsonObject.getJSONArray("Android");
                for (int i=0;i<=result.length();i++){

                    JSONObject data=result.getJSONObject(i);
                    show_email=data.getString("Email");
                    show_pass=data.getString("Password");
                    show_status=data.getString("status");
                    show_message=data.getString("message");
                    Log.e("status", show_status);
                    main_module = new Employee_Registration_POJO(show_status, show_message);
                    Data_list.add(main_module);



                }



            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        //Toast.makeText(sec.this,"Log In Succusefully "+ show_status,Toast.LENGTH_SHORT).show();
        if(show_status.equals("1"))
        {
            Toast.makeText(EmployeeLogin.this,"Log In Succusefully ",Toast.LENGTH_SHORT).show();
            //Intent in = new Intent(EmployeeLogin.this,HomeActivity.class);
            //startActivity(in);
        }
        else
        {

            Toast.makeText(EmployeeLogin.this,"Invalid Data...",Toast.LENGTH_LONG).show();

        }
    }

}
