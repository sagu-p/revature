package com.vivek.paradiseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class sec extends AppCompatActivity {

    long bs;
    public EditText email1,pass1;
    public String email,pass;
    public Button b1;
    public TextView t1,t2;
    public String url;
    public String show_email=null;
    public String show_pass=null;
    public String show_status=null;
    public String show_message=null;
    public Ragistration_POJO main_module;
    public ArrayList<Ragistration_POJO> Data_list = new ArrayList<>();


    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public static final String MyPrefrences = "MyPref";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sec);

//        Intent in=getIntent();

        email1=(EditText) findViewById(R.id.email_l);
        pass1=(EditText) findViewById(R.id.pass_l);
        b1=(Button) findViewById(R.id.log_button);
        t1=(TextView) findViewById(R.id.textView);
        t2=(TextView) findViewById(R.id.textView2);
        //t2.setText(getIntent().getExtras().getString("name"));

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sec.this,MainActivity.class));
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email=email1.getText().toString();
                pass=pass1.getText().toString();


                //url="http://"+IP.ip+"/project/log.php?c_email='"+email+"'&&c_pass='"+pass;
                //url=http://localhost/project/customer_profile.php?c_email=w@q.com
                url="http://"+IP.ip+"/project_para_salon/login_s.php?c_email="+email+"&&c_pass="+pass;

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

                        Toast.makeText(sec.this,error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });


        RequestQueue rq= Volley.newRequestQueue(sec.this);
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
                    main_module = new Ragistration_POJO(show_status, show_message);
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
            Toast.makeText(sec.this,"Log In Succusefully ",Toast.LENGTH_SHORT).show();
            /*Intent in = new Intent(sec.this,HomeActivity.class);
            startActivity(in);*/

            pref = getApplicationContext().getSharedPreferences(MyPrefrences, Context.MODE_PRIVATE);
            editor =pref.edit();
            editor.putString("Email",email);
            editor.putString("Password",pass);
            //editor.putString("Name",name);
            editor.commit();

            Intent in = new Intent(sec.this,NavigationHomeActivity.class);
            startActivity(in);

        }
        else
        {

            Toast.makeText(sec.this,"Invalid Data...",Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onBackPressed() {
        if (bs+2000>System.currentTimeMillis()) {
            super.onBackPressed();
            //return;
            finish();
        }
        else {
            bs=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"Touch again to Exit.",Toast.LENGTH_SHORT).show();
        }
    }

}
