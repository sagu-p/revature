package com.vivek.paradiseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Appointment extends AppCompatActivity {

    public Button cur_app, pre_app, cal_app, pen_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        pen_app= findViewById(R.id.pen_app);
        cur_app= findViewById(R.id.con_app);
        pre_app= findViewById(R.id.pre_app);
        cal_app= findViewById(R.id.cal_app);

        pen_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Appointment.this,ShowAppointment.class);
                in.putExtra("status", "0");
                startActivity(in);

            }
        });

        cur_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Appointment.this,ShowAppointment.class);
                in.putExtra("status", "1");
                startActivity(in);

            }
        });

        pre_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Appointment.this,ShowAppointment.class);
                in.putExtra("status", "2");
                startActivity(in);
            }
        });

        cal_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Appointment.this,ShowAppointment.class);
                in.putExtra("status", "-1");
                startActivity(in);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Appointment.this,NavigationHomeActivity.class));
    }
}
