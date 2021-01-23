package com.vivek.paradiseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class ShowAppointment extends AppCompatActivity {

    ListView listView;
    public String url, Email;

    public String app_id, c_id, service_id, service_name, emp_name, app_date, app_time, app_status;

    public String show_status, status;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public Appointment_POJO main_module;
    public ArrayList<Appointment_POJO> Data_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_appointment);

        listView=findViewById(R.id.listView);

        pref = getApplicationContext().getSharedPreferences(sec.MyPrefrences, Context.MODE_PRIVATE);
        editor = pref.edit();

        Email = pref.getString("Email",null);

        Intent i = getIntent();
        status = i.getStringExtra("status");

        //http://localhost/project_para_salon/appointment.php?c_id=sagar&&status=2
        url = "http://"+IP.ip+"/project_para_salon/appointment.php?c_id="+Email+"&&status="+status;

        getData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tv = (TextView) view.findViewById(R.id.app_id);
                String str = tv.getText().toString().trim();

                //Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();

                if (status.equals("0") || status.equals("1")) {
                    Intent in = new Intent(ShowAppointment.this,Appointment_Detail_Pre_Con.class);
                    in.putExtra("id", str);
                    startActivity(in);
                }
                else {
                    Intent in = new Intent(ShowAppointment.this, Appointment_detail.class);
                    in.putExtra("id", str);
                    startActivity(in);
                }


            }
        });


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

                    app_id=data.getString("app_id");
                    c_id=data.getString("c_id");
                    service_id=data.getString("service_id");
                    service_name=data.getString("service_name");
                    emp_name=data.getString("emp_name");
                    app_date=data.getString("app_date");
                    app_time=data.getString("app_time");
                    app_status=data.getString("app_status");

                    Log.e("status", app_date);

                    //Data_list.add(module)

         //           Log.e("Name:",name);

                    main_module = new Appointment_POJO(app_id, app_date, app_time);
                    Data_list.add(main_module);



                    //Toast.makeText(getApplicationContext(),show_message,Toast.LENGTH_SHORT).show();
                }



            }
        } catch (JSONException e) {

            //String h="shb";
            //Log.e("h",h);
            //Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        ShowAppointment.CustomAdapter addepter = new ShowAppointment.CustomAdapter(ShowAppointment.this,R.layout.appointment_show,Data_list);
        listView.setAdapter(addepter);

    }

    public class CustomAdapter extends ArrayAdapter<Appointment_POJO> {

        Activity activity;
        ArrayList<Appointment_POJO> detail_info;
        LayoutInflater inflater;
        String name_replace;

        public CustomAdapter(ShowAppointment showAppointment, int appointment_show, ArrayList<Appointment_POJO> data_list) {
            super(showAppointment, appointment_show, data_list);
            this.activity=showAppointment;
            this.detail_info = data_list;
        }

        public class RecordHolder {

            TextView date;
            TextView id11;
            TextView time;
            //ImageView img;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Appointment_POJO dinfo = this.detail_info.get(position);
            ShowAppointment.CustomAdapter.RecordHolder holder = null;
            //holder = new ProductShow.CustomAdapter.RecordHolder();
            holder = new ShowAppointment.CustomAdapter.RecordHolder();
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.appointment_show, parent, false);

            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.id11 = (TextView) convertView.findViewById(R.id.app_id);
            holder.time = (TextView) convertView.findViewById(R.id.time);

 /*           holder.img=convertView.findViewById(R.id.img);
            convertView.setTag(holder);*/

            Log.e("id:", dinfo.getApp_id());


            /*name_replace=dinfo.getName();
            name_replace=name.replaceAll("%20"," ");*/


            holder.id11.setText(dinfo.getApp_id());
            holder.date.setText(dinfo.getApp_date());
            holder.time.setText(dinfo.getApp_time());


//            Picasso.with(using_product_show.this).load(dinfo.getImg()).placeholder(R.drawable.ic_launcher_background).into(holder.img);


            return convertView;
        }




    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(ShowAppointment.this,Appointment.class));
    }
}
