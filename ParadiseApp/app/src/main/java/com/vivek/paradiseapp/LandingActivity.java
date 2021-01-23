package com.vivek.paradiseapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LandingActivity extends AppCompatActivity {

    public SharedPreferences pref;
    public String str_email, str_pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        pref = getSharedPreferences(sec.MyPrefrences, Context.MODE_PRIVATE);
        pref.edit();

        //Toast.makeText( LandingActivity.this,"Email "+str_email , Toast.LENGTH_SHORT).show();
        //Toast.makeText( LandingActivity.this,"Password "+str_pass , Toast.LENGTH_SHORT).show();
        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);

                    //Intent i = new Intent(getApplicationContext(), sec.class);
                    /*Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();*/
                    str_email = pref.getString("Email", "");
                    str_pass = pref.getString("Password", "");



                    if (str_email.equals("") && str_pass.equals("")) {
                        Intent i = new Intent(getApplicationContext(), sec.class);
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(getApplicationContext(), NavigationHomeActivity.class);
                        startActivity(i);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

    }
}
