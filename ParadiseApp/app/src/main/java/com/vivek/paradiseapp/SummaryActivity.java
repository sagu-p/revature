package com.vivek.paradiseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {



    public String appointmentid,email,salonid,employeeid,employeename,subcatid,subcatname,subcatprice,date,time;
    public String URL,Email,SalonID,SubcatID,SubcatName,EmpID,EmpName,Date,Time;
    ListView list,listproduct,listsalon;
    public String cat_id;
    String productid = "";
    String productname = "";
    String productimage = "";
    String productprice = "";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private ProgressDialog loading;
    public int i;
    AppointmentService main_module;
    ArrayList<AppointmentService> Data_list = new ArrayList<AppointmentService>();;
    /*AppointmentProduct main_moduleProduct;
    ArrayList<AppointmentProduct> Data_listProduct = new ArrayList<AppointmentProduct>();;
    Salon main_moduleSalon;
    ArrayList<Salon> Data_listSalon = new ArrayList<Salon>();*/
    public int gender, total_price=0;
    public String gnder;
    public String catid,s;
    private String subcatimage;
    TextView app_date, app_emp_name, app_time,app_emp_id, salon_add, salon_call;

    public String service_names=" ",service_ids=" ",price;
    public String book_url, del_cart_url;

    public String salon_id="",salonname="",salonadd="",salonimage="",salonlongitude="",salonlatitude="",saloncall="",salonemail="",salonrate="";
    public String call;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        list = (ListView) findViewById(R.id.finalList);
        //listproduct = (ListView) findViewById(R.id.product_List);
        //listsalon = (ListView) findViewById(R.id.salon_list);
        Button btn_bookAppointment = (Button) findViewById(R.id.book);

        app_emp_id = (TextView) findViewById(R.id.txt_name8);
        app_emp_name = (TextView) findViewById(R.id.txt_name3);
        app_date = (TextView) findViewById(R.id.txt_name4);
        app_time = (TextView) findViewById(R.id.txt_name6);
        /*salon_add = (TextView)findViewById(R.id.list_item2);
        salon_call = (TextView)findViewById(R.id.list_item3);*/

        pref = getApplicationContext().getSharedPreferences(sec.MyPrefrences, Context.MODE_PRIVATE);
        editor = pref.edit();

        Email = pref.getString("Email",null);


        Intent in = getIntent();

        //SalonID = in.getStringExtra("SalonID");
        EmpID = in.getStringExtra("EmpID");
        EmpName = in.getStringExtra("EmpName");
        /*SubcatID = in.getStringExtra("SubcatId");
        SubcatName = in.getStringExtra("SubcatName");*/
        Date = in.getStringExtra("Date");
        Time = in.getStringExtra("Time");

        Date=Date.replace("%20", " ");
        Date=Date.replace("%2C", ",");
        Time=Time.replace("%20", " ");

        app_date.setText(Date);
        app_time.setText(Time);
        app_emp_name.setText(EmpName);
        app_emp_id.setText(EmpID);

        //Toast.makeText(SummaryActivity.this, "Date" +Date, Toast.LENGTH_LONG).show();
        //Toast.makeText(SummaryActivity.this, "time" +Time, Toast.LENGTH_LONG).show();
        //Toast.makeText(SummaryActivity.this, "name" +EmpName, Toast.LENGTH_LONG).show();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*price=Integer.toString(total_price);
        Toast.makeText(getApplicationContext(),"Total Price: "+total_price,Toast.LENGTH_SHORT).show();*/



        URL = "http://" + IP.ip + "/project_para_salon/salon_service_cart_select.php?email="+Email;
        new DoInBackgroundCategory().execute();

        btn_bookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SummaryActivity.this, "Appoitment Date:" +Date, Toast.LENGTH_LONG).show();

                service_ids=service_ids.replace(" ","%20");
                service_names=service_names.replace(" ","%20");

                service_names=service_names.replace(",","%2C");

                Date=Date.replace(" ","%20");
                Date=Date.replace(",","%2C");

                Time=Time.replace(" ","%20");



                book_url="http://"+IP.ip+"/project_para_salon/book_app.php?c_id="+Email+"&&service_id="+service_ids+"&&service_name="+service_names+"&&emp_name="+EmpName+"&&app_date="+Date+"&&app_time="+Time+"&&status=0";
                getAppointment();

                del_cart_url="http://"+IP.ip+"/project_para_salon/cart_delete.php?email="+Email;
                delCart();

                //sendEmail();
                //createNotification(v);

                /*Intent in = new Intent(SummaryActivity.this, NavigationHomeActivity.class);
                startActivity(in);*/

                startActivity(new Intent(SummaryActivity.this,NavigationHomeActivity.class));


            }
        });

    }


    //Appointment Book
    public void getAppointment() {
        Log.e("url",book_url);
        StringRequest sr=new StringRequest(book_url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                showjson_book(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(SummaryActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();


                    }
                });
        RequestQueue rq= Volley.newRequestQueue(SummaryActivity.this);
        rq.add(sr);
    }


    public void showjson_book(String response) {

        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(response);
            String msg=jsonObject.getString("status");

            if(msg.equals("1")){
                Toast.makeText(getApplicationContext(),"Apppointment Request sent...",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(SummaryActivity.this, "Appointment not done...", Toast.LENGTH_SHORT).show();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public void delCart() {
        Log.e("url",del_cart_url);
        StringRequest sr=new StringRequest(del_cart_url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                showjson_delCart(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(SummaryActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();


                    }
                });
        RequestQueue rq= Volley.newRequestQueue(SummaryActivity.this);
        rq.add(sr);
    }


    public void showjson_delCart(String response) {

        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(response);
            String msg=jsonObject.getString("status");


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public class DoInBackgroundCategory extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            getData();
            //getDataProduct();
            //getDataSalon();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(SummaryActivity.this, "Please Wait...", "Fetching..", false, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public void getData() {

        Log.e("Inserted Elements>>",URL);

        StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }


        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SummaryActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(SummaryActivity.this);
        requestQueue.add(stringRequest);
    }

    public void showJSON(String response) { try {
        Log.e("Error>>","Enter in ShowJson");
        JSONObject jsonObject = new JSONObject(response);
        JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

        for (i = 0; i <= result.length(); i++) {
            JSONObject category_data = result.getJSONObject(i);

            //appointmentid = category_data.getString(Config.KEY_app_id);
            subcatid = category_data.getString(Config.KEY_sub_cat_id);
            subcatprice = category_data.getString(Config.KEY_sub_cat_price);
            subcatname = category_data.getString(Config.KEY_sub_cat_name);
            subcatimage = category_data.getString(Config.KEY_sub_cat_image);

            //subcatname1=subcatname;


            /*int price=Integer.parseInt(subcatprice);
            total_price+=price;*/


            //service name
            if(service_names.equals(" "))
            {
                service_names=subcatname;
            }
            else
            {
                service_names=service_names+", "+subcatname;
            }

            //service id
            if(service_ids.equals(" "))
            {
                service_ids=subcatid;
            }
            else
            {
                service_ids=service_ids+", "+subcatid;
            }

            /*service_names=service_names+subcatname;*/

            Log.e("Service Names:",service_names);
            //Toast.makeText(getApplicationContext(),service_names,Toast.LENGTH_SHORT).show();

            Log.e("SubCategory_name>>", subcatname);
            main_module = new AppointmentService(appointmentid,subcatid, subcatname, subcatprice,subcatimage);
            Data_list.add(main_module);
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }

        /*pref = getApplicationContext().getSharedPreferences(MyPrefrences, Context.MODE_PRIVATE);
        editor =pref.edit();
        editor.putString("SubcatId",subcatid);
        editor.putString("SubcatName",subcatname);
        editor.commit();*/

        list.setAdapter(new CustomeAdpaterMainCategory(SummaryActivity.this, R.layout.activity_summary, Data_list));
        // list.setAdapter(new CustomeAdpaterMainCategory(SummaryActivity.this, R.layout.activity_summary, Data_list));
    }
    public class CustomeAdpaterMainCategory extends ArrayAdapter<AppointmentService> {
        private Activity activity;
        private LayoutInflater inflater;
        private ArrayList<AppointmentService> detailInfo;

        public CustomeAdpaterMainCategory(SummaryActivity summaryActivity, int inside_list_summary, ArrayList<AppointmentService> data_list) {

            super(summaryActivity, inside_list_summary, data_list);


            this.activity = summaryActivity;
            this.detailInfo = data_list;
        }

        class RecordHolder {
            TextView id;
            TextView categoryPrice;
            TextView categoryName;
            TextView date;
            TextView time;
            ImageView image;
            ImageButton ib_add;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            AppointmentService dInfo = this.detailInfo.get(position);
            RecordHolder holder = null;
            if (convertView == null) {
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                holder = new RecordHolder();
                convertView = inflater.inflate(R.layout.inside_list_summary, parent, false);
                holder.id = (TextView) convertView.findViewById(R.id.tvid1);
                holder.image = (ImageView) convertView.findViewById(R.id.img_category1);
                holder.categoryName = (TextView) convertView.findViewById(R.id.txt_name1);
                holder.categoryPrice = (TextView) convertView.findViewById(R.id.txt_name2);
                //holder.ib_add = (ImageButton) convertView.findViewById(R.id.ib_add);
                convertView.setTag(holder);
            } else {
                holder = (RecordHolder) convertView.getTag();
            }
            holder.id.setText(dInfo.getAsubcatid());
            holder.categoryName.setText(dInfo.getAsubcatname());
            holder.categoryPrice.setText(dInfo.getAsubcatprice());
            /*holder.empName.setText(EmpName);
            holder.date.setText(Date);
            holder.time.setText(Time);*/
            //holder.image.setImageResource(dInfo.getCategory_image());

            cat_id = dInfo.getAsubcatid().toString().trim();
            Picasso.with(SummaryActivity.this).load(dInfo.getAimage()).placeholder(R.mipmap.ic_launcher).into(holder.image);
            //Picasso.with(SummaryActivity.this).load(R.mipmap.ic_launcher).into(holder.image);
          //  Toast.makeText(SummaryActivity.this, "DATA >> " + dInfo.getAsubcatname(), Toast.LENGTH_SHORT).show();

            return convertView;

        }
    }

}
