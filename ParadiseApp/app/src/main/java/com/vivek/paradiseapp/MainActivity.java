package com.vivek.paradiseapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    long bs;
    public String url;
    public String add, name, num, email, pass, bdate, gen;
    String rp, type;
    public EditText name1, email1, num1, pass1, bdate1, add1;
    RadioButton f, m;
    //private View name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name1=(EditText)findViewById(R.id.name);
        email1=(EditText)findViewById(R.id.email);
        num1=(EditText)findViewById(R.id.mob_num);
        pass1=(EditText)findViewById(R.id.pass);
        bdate1=(EditText)findViewById(R.id.bdate);
        add1=(EditText)findViewById(R.id.add);
        f=(RadioButton)findViewById(R.id.female);
        m=(RadioButton)findViewById(R.id.male);




        Button b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //name=name1.getText().toString();
                //num=num1.getText().toString();
                // email=email1.getText().toString();
                // pass=pass1.getText().toString();
                //bdate=bdate1.getText().toString();
                //add=add1.getText().toString();

                rp = "0";

                type ="Normal";

                if (f.isChecked())
                {
                    gen=f.getText().toString();
                }
                else
                {
                    gen=m.getText().toString();
                }

                /////////////////////////////////////////////////////////////

                try{
                    name = URLEncoder.encode(name1.getText().toString().trim(),"UTF-8");
                }
                catch(UnsupportedOperationException e)
                {
                    e.printStackTrace();
                }
                catch(UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
                try{
                    email = URLEncoder.encode(email1.getText().toString().trim(),"UTF-8");
                }
                catch(UnsupportedOperationException e)
                {
                    e.printStackTrace();
                }
                catch(UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
                try {
                    pass = URLEncoder.encode(pass1.getText().toString().trim(), "UTF-8");
                }
                catch(UnsupportedOperationException e)
                {
                    e.printStackTrace();
                }
                catch(UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
                try{
                    num = URLEncoder.encode(num1.getText().toString().trim(),"UTF-8");
                }
                catch(UnsupportedOperationException e)
                {
                    e.printStackTrace();
                }
                catch(UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }


                try{
                    bdate = URLEncoder.encode(bdate1.getText().toString().trim(),"UTF-8");
                }
                catch(UnsupportedOperationException e)
                {
                    e.printStackTrace();
                }
                catch(UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }

                try{
                    add = URLEncoder.encode(add1.getText().toString().trim(),"UTF-8");
                }
                catch(UnsupportedOperationException e)
                {
                    e.printStackTrace();
                }
                catch(UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }


                if(name1.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, "enter valid name", Toast.LENGTH_SHORT).show();
                    //name1.setError("Enter Valid Name");
                }
                else if(num1.getText().toString().equals("") || num.length()!=10)
                {
                    Toast.makeText(MainActivity.this,"Enter valid number of length of 10",Toast.LENGTH_SHORT).show();
                    //num1.setError("Enter Valid Mobile Number");
                }
                else if(email1.getText().toString().trim().equals("") || !Patterns.EMAIL_ADDRESS.matcher(email1.getText().toString().trim()).matches())
                {
                    Toast.makeText(MainActivity.this, "enter valid email", Toast.LENGTH_SHORT).show();
                    //email1.setError("Enter Valid Email");
                }
                else if(pass1.getText().toString().trim().equals("")   ||  ( (pass1.getText().toString().trim().length()) < 4 && ( pass1.getText().toString().trim().length() ) < 8  )  )
                {
                    Toast.makeText(MainActivity.this, "enter valid password", Toast.LENGTH_SHORT).show();
                    //pass1.setError("Enter Valid Password Between 4 to 8 character");

                }
                else if(bdate1.getText().toString().trim().equals(""))
                {
                    Toast.makeText(MainActivity.this,"Enter Birth date",Toast.LENGTH_SHORT).show();
                    //bdate1.setError("Enter Valid Birthdate");
                }
                else if(add1.getText().toString().trim().equals("") )
                {
                    Toast.makeText(MainActivity.this,"Enter Address",Toast.LENGTH_SHORT).show();
                    //add1.setError("Enter Address");
                }

                /*else if(!pass1.getText().toString().trim().equals(signup_confirm_password.getText().toString().trim()))
                {
                    signup_confirm_password.setError("Enter Valid Confirm Password ");
                }*/
/*                else if(num1.getText().toString().equals("") || num.length()!=10)
                {
                    num1.setError("Enter Valid Mobile Number");
                }
                else if(bdate1.getText().toString().trim().equals(""))
                {
                    bdate1.setError("Enter Valid Birthdate");
                }
                else if(add1.getText().toString().trim().equals("") )
                {
                    add1.setError("Enter Valid Email");
                }*/
                else {

                    url="http://"+IP.ip+"/project_para_salon/select.php?c_name="+name+"&&c_num="+num+"&&c_email="+email+"&&c_pass="+pass+
                            "&&c_gen="+gen+"&&c_add="+add+"&&c_bdate="+bdate+"&&c_rp="+rp+"&&c_type="+type;

                    getData();

                    Intent in=new Intent(MainActivity.this,sec.class);
                    in.putExtra("name",name);
                    startActivity(in);
                    //Toast.makeText(getApplicationContext(),"Sign Up Successfully...",Toast.LENGTH_SHORT).show();

                }
                ////////////////////////////////////////////////////////////////
                //Intent i=new Intent(MainActivity.this,sec.class);


            }
        });

    }

    public void getData() {
        Log.e("url",url);
        StringRequest sr=new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                showjson(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();


                    }
                });
        RequestQueue rq= Volley.newRequestQueue(MainActivity.this);
        rq.add(sr);
    }

    public void showjson(String response) {

        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(response);
            String msg=jsonObject.getString("status");

            if(msg.equals("1")){

                //Intent i=new Intent(MainActivity.this,sec.class);
                Intent in=new Intent(MainActivity.this,sec.class);
                startActivity(in);

            }
            else{
                Toast.makeText(MainActivity.this, "Enter valid Details", Toast.LENGTH_SHORT).show();

            }

            Toast.makeText(MainActivity.this, "aaa "+msg, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    /*@Override
    public void onBackPressed() {
        if (bs+2000>System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        }
        else {
            bs=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"Touch again to Exit.",Toast.LENGTH_SHORT).show();
        }
    }*/
}
