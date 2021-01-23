package com.vivek.paradiseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class EmployeeRegetrationActivity extends AppCompatActivity {

    public String name1,email1,mob1,pass1,dob1,add1,qual1;
    public String url;
    public EditText name,email,mob,pass,dob,add,qual;
    public RadioButton male,female;

    String gen;
    String sal="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_regetration);

        name=findViewById(R.id.emp_name);
        email=findViewById(R.id.emp_email);
        mob=findViewById(R.id.emp_num);
        pass=findViewById(R.id.emp_pass);
        dob=findViewById(R.id.emp_date);
        add=findViewById(R.id.emp_add);
        qual=findViewById(R.id.emp_quali);

        male=findViewById(R.id.male);
        female=findViewById(R.id.female);


        Button b1=findViewById(R.id.emp_signup);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



        try{
            name1 = URLEncoder.encode(name.getText().toString().trim(),"UTF-8");
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
            mob1 = URLEncoder.encode(mob.getText().toString().trim(),"UTF-8");
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
            email1= URLEncoder.encode(email.getText().toString().trim(),"UTF-8");
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
            pass1 = URLEncoder.encode(pass.getText().toString().trim(),"UTF-8");
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
            dob1 = URLEncoder.encode(dob.getText().toString().trim(),"UTF-8");
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
            add1 = URLEncoder.encode(add.getText().toString().trim(),"UTF-8");
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
            qual1 = URLEncoder.encode(qual.getText().toString().trim(),"UTF-8");
        }
        catch(UnsupportedOperationException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        if(male.isChecked())
        {
            gen=male.getText().toString();
        }
        else
        {
            gen=female.getText().toString();
        }


        if(name.getText().toString().trim().equals("")) {
            Toast.makeText(EmployeeRegetrationActivity.this, "enter valid name", Toast.LENGTH_SHORT).show();
            //name1.setError("Enter Valid Name");
        }
        else if(mob.getText().toString().equals("") || mob1.length()!=10)
        {
            Toast.makeText(EmployeeRegetrationActivity.this,"Enter valid number of length of 10",Toast.LENGTH_SHORT).show();
            //num1.setError("Enter Valid Mobile Number");
        }
        else if(email.getText().toString().trim().equals("") || !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches())
        {
            Toast.makeText(EmployeeRegetrationActivity.this, "enter valid email", Toast.LENGTH_SHORT).show();
            //email1.setError("Enter Valid Email");
        }
        else if(pass.getText().toString().trim().equals("")   ||  ( (pass.getText().toString().trim().length()) < 4 && ( pass.getText().toString().trim().length() ) < 8  )  )
        {
            Toast.makeText(EmployeeRegetrationActivity.this, "enter valid password", Toast.LENGTH_SHORT).show();
            //pass1.setError("Enter Valid Password Between 4 to 8 character");

        }
        else if(dob.getText().toString().trim().equals(""))
        {
            Toast.makeText(EmployeeRegetrationActivity.this,"Enter Birth date",Toast.LENGTH_SHORT).show();
            //bdate1.setError("Enter Valid Birthdate");
        }
        else if(add.getText().toString().trim().equals("") )
        {
            Toast.makeText(EmployeeRegetrationActivity.this,"Enter Address",Toast.LENGTH_SHORT).show();
            //add1.setError("Enter Address");
        }
        else if(qual.getText().toString().trim().equals("") )
        {
            Toast.makeText(EmployeeRegetrationActivity.this,"Enter Qualification",Toast.LENGTH_SHORT).show();
            //add1.setError("Enter Address");
        }
        else
        {

            //url="http://"+IP.ip+"/project/select.php?c_name="+name+"&&c_num="+num+"&&c_email="+email+"&&c_pass="+pass+"
            // &&c_gen="+gen+"&&c_add="+add+"&&c_bdate="+bdate+"&&c_rp="+rp+"&&c_type="+type;

            url="http://"+IP.ip+"/project/emp_insert.php?emp_name="+name1+"&&emp_mob_num="+mob1+"&&emp_email="+email1+"&&emp_pass="+pass1+"&&emp_gen="+gen+"&&emp_bdate="+dob1+"&&emp_qualification="+qual1+"&&emp_sal="+sal+"&&emp_add="+add1;

            getData();
        }

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


                        Toast.makeText(EmployeeRegetrationActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();


                    }
                });
        RequestQueue rq= Volley.newRequestQueue(EmployeeRegetrationActivity.this);
        rq.add(sr);
    }

    public void showjson(String response) {

        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(response);
            String msg=jsonObject.getString("status");

            if(msg.equals("1")){



                Intent in=new Intent(EmployeeRegetrationActivity.this,EmployeeLogin.class);
                startActivity(in);

                Toast.makeText(EmployeeRegetrationActivity.this,"Employee detail Submitted",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(EmployeeRegetrationActivity.this, "Enter valid Details", Toast.LENGTH_SHORT).show();

            }

            //Toast.makeText(EmployeeRegetrationActivity.this, "aaa "+msg, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
