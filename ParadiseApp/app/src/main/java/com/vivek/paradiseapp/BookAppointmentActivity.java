package com.vivek.paradiseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.model.CalendarEvent;
import devs.mulham.horizontalcalendar.utils.CalendarEventsPredicate;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
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
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static com.vivek.paradiseapp.sec.MyPrefrences;

public class BookAppointmentActivity extends AppCompatActivity {

    HorizontalCalendar horizontalCalendar;
    String selectedDateStr;
    HorizontalListView listview;
    String item;

    private HorizontalListView list;
    public String URL;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private ProgressDialog loading;
    public int i;
    SalonRate main_module;
    ArrayList<SalonRate> Data_list = new ArrayList<SalonRate>();;
    public String s,SalonID;
    public String cat_id,EmpID,SubcatID,SubcatName;
    public String employeeid="",employeename="",employeeimage="",email,subcatname1,Date,time;
    private Button btn_next;
    public String emp_name;
    public String Emp_name;
    public int  employeerate;
    public Toolbar toolbar;
    public String get_time="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        btn_next = (Button) findViewById(R.id.next);

        pref = getSharedPreferences(sec.MyPrefrences, Context.MODE_PRIVATE);
        pref.edit();
        email = pref.getString("Email", null);

        Intent i = getIntent();
        //SalonID=i.getStringExtra("SalonID");
        SubcatID = i.getStringExtra("SubcatID");
        SubcatName = i.getStringExtra("SubcatName");
        //Toast.makeText(BookAppointmentActivity.this, "Selected : " + SubcatID, Toast.LENGTH_LONG).show();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(BookAppointmentActivity.this, "Date" +selectedDateStr, Toast.LENGTH_LONG).show();
                //Toast.makeText(BookAppointmentActivity.this, "time" +item, Toast.LENGTH_LONG).show();
                new DoInBackgroundAppointment().execute();
                Intent in = new Intent(BookAppointmentActivity.this, SummaryActivity.class);
                //in.putExtra("SalonID", SalonID);
                in.putExtra("Date",selectedDateStr);
                in.putExtra("Time",item);
                in.putExtra("EmpName",Emp_name);
                in.putExtra("EmpID",EmpID);
                startActivity(in);
            }
        });

        /////////////////////////////////////////////////////////////////////////////////

//Toast.makeText(BookAppointmentActivity.this, gnder,Toast.LENGTH_LONG).show();
        list = (HorizontalListView) findViewById(R.id.emp);

        //Toast.makeText(BookAppointmentActivity.this, gender,Toast.LENGTH_LONG).show();

        new DoInBackgroundEmployee().execute();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv_name = (TextView) view.findViewById(R.id.tvid);
                EmpID = tv_name.getText().toString().trim();
                TextView tv_name1 = (TextView) view.findViewById(R.id.txt_name);
                Emp_name = tv_name1.getText().toString().trim();
                Toast.makeText(BookAppointmentActivity.this, "Employee Selected : " +Emp_name, Toast.LENGTH_LONG).show();
                //Toast.makeText(BookAppointmentActivity.this, "Employee : " +EmpID, Toast.LENGTH_SHORT).show();


                getDataTimeSelect();
                //list.getChildAt(i).setBackgroundColor(Color.BLUE);
               /* Intent i1 = new Intent(BookAppointmentActivity.this, Employee_detailActivity.class);
                i1.putExtra("EmpID", EmpID);
                i1.putExtra("SalonID", salonid);
                startActivity(i1);*/
            }
        });

        ////////////////////////////////////////////////////////////////////////////////

        String[] values = {"10:00 am", "11:00 am", "12:00 pm", "01:00 pm", "02:00 pm",
                "03:00 pm", "04:00 pm", "05:00 pm", "06:00 pm", "07:00 pm", "08:00 pm"};

        listview = (HorizontalListView) findViewById(R.id.hlist);

        /* start id2 months ago from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -2);

        /* end after id2 months from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 2);

        // Default Date set to Today.
        final Calendar defaultSelectedDate = Calendar.getInstance();

        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .configure()
                .formatTopText("MMM")
                .formatMiddleText("dd")
                .formatBottomText("EEE")
                .showTopText(true)
                .showBottomText(true)
                .textColor(Color.LTGRAY, Color.WHITE)
                .colorTextMiddle(Color.LTGRAY, Color.parseColor("#ffd54f"))
                .end()
                .defaultSelectedDate(defaultSelectedDate)
                .addEvents(new CalendarEventsPredicate() {

                    Random rnd = new Random();

                    @Override
                    public List<CalendarEvent> events(Calendar date) {
                        List<CalendarEvent> events = new ArrayList<>();
                        int count = rnd.nextInt(6);

                        for (int i = 0; i <= count; i++) {
                            events.add(new CalendarEvent(Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)), "event"));
                        }

                        return events;
                    }
                })
                .build();


        Log.i("Default Date", DateFormat.format("EEE, MMM d, yyyy", defaultSelectedDate).toString());

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                selectedDateStr = DateFormat.format("EEE, MMM d, yyyy", date).toString();

                pref = getApplicationContext().getSharedPreferences(MyPrefrences, Context.MODE_PRIVATE);
                editor =pref.edit();
                editor.putString("Date",selectedDateStr);
                editor.commit();

                //Toast.makeText(BookAppointmentActivity.this, selectedDateStr + " selected!", Toast.LENGTH_SHORT).show();
                Log.i("onDateSelected", selectedDateStr + " - Position = " + position);
            }

        });

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                horizontalCalendar.goToday(false);
            }
        });*/


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*for(int j = 0 ; j<time_array.length; j++) {
            if (listview.getChildAt(j).isEnabled()) {
                listview.getChildAt(j).setEnabled(false);
            }
        }*/


        final ArrayAdapter<String> aItems = new ArrayAdapter<String>(this, R.layout.inside_time, R.id.str_text, values);
        listview.setAdapter(aItems);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                item = (String) parent.getItemAtPosition(position);

                //listview.getChildAt(position).setEnabled(false);


                //listview.getChildAt(position).setEnabled(false);

                pref = getApplicationContext().getSharedPreferences(MyPrefrences, Context.MODE_PRIVATE);
                editor =pref.edit();
                editor.putString("Time",item);
                editor.commit();

                Toast.makeText(BookAppointmentActivity.this, item + " selected", Toast.LENGTH_LONG).show();
            }

        });
    }


    //////////////////////////////////////////////////////////////////////////////
    public void getDataTimeSelect() {
        int st=0, da=15;
        URL = "http://" + IP.ip + "/project_para_salon/salon_employee_booked_time.php?emp_id=" +EmpID+"&&date="+da+"&&app_status="+st;

        Log.e("Inserted Elements>>",URL);

        StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSONTimeSelect(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookAppointmentActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(BookAppointmentActivity.this);
        requestQueue.add(stringRequest);

    }

    public void showJSONTimeSelect(String response) {


        try {
            Log.e("Error>>","Enter in ShowJson");
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (i = 0; i <= result.length(); i++) {
                JSONObject category_data = result.getJSONObject(i);
                get_time = category_data.getString("time");

                Log.e("time>>", get_time);

                if (listview.getChildAt(i).isEnabled()) {
                    listview.getChildAt(i).setEnabled(false);
                    listview.getChildAt(i).setBackgroundColor(Color.GRAY);
                }

                /*main_module = new SalonRate(get_time);
                Data_list.add(main_module);*/
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////

    public class DoInBackgroundEmployee extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            getData();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(BookAppointmentActivity.this, "Please Wait...", "Fetching..", false, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
    public void getData() {

        URL = "http://" + IP.ip + "/project_para_salon/salon_employee_select.php";

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
                        Toast.makeText(BookAppointmentActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(BookAppointmentActivity.this);
        requestQueue.add(stringRequest);
    }

    public void showJSON(String response) {
        try {
            Log.e("Error>>","Enter in ShowJson");
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (i = 0; i <= result.length(); i++) {
                JSONObject category_data = result.getJSONObject(i);
                employeeid = category_data.getString(Config.KEY_employee_id);
                employeename = category_data.getString(Config.KEY_employee_name);
                employeeimage = category_data.getString(Config.KEY_employee_image);
                employeerate = category_data.getInt(Config.KEY_employee_rate);

                Log.e("Employee_name>>", employeename);
                main_module = new SalonRate(employeeid, employeename, employeeimage,employeerate);
                Data_list.add(main_module);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*pref = getApplicationContext().getSharedPreferences(MyPrefrences, Context.MODE_PRIVATE);
        editor =pref.edit();
        editor.putString("EmpId",employeeid);
        editor.putString("EmpName",employeename);
        editor.commit();*/

        list.setAdapter(new CustomeAdpaterMainCategory(BookAppointmentActivity.this, R.layout.inside_grid_salon, Data_list));
    }


    public class CustomeAdpaterMainCategory extends ArrayAdapter<SalonRate> {
        private Activity activity;
        private LayoutInflater inflater;
        private ArrayList<SalonRate> detailInfo;

        public CustomeAdpaterMainCategory(BookAppointmentActivity bookAppointmentActivityActivity, int inside_grid_home, ArrayList<SalonRate> data_list) {

            super(bookAppointmentActivityActivity,inside_grid_home,data_list);


            this.activity = bookAppointmentActivityActivity;
            this.detailInfo = data_list;
        }
        class RecordHolder {
            TextView employeeName,employeeRate;
            TextView employeeId;
            ImageView image;
            RatingBar Rate;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            SalonRate dInfo = this.detailInfo.get(position);
            BookAppointmentActivity.CustomeAdpaterMainCategory.RecordHolder holder = null;
            if (convertView == null) {
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                holder = new RecordHolder();
                convertView = inflater.inflate(R.layout.inside_grid_salon, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.img_category);
                holder.employeeName = (TextView) convertView.findViewById(R.id.txt_name);
                //holder.employeeRate = (TextView) convertView.findViewById(R.id.txt_name_rate);
                holder.employeeId = (TextView) convertView.findViewById(R.id.tvid);
                //holder.Rate=(RatingBar) convertView.findViewById(R.id.rate);
                //holder.productId = (TextView) convertView.findViewById(R.id.tvid1);
                convertView.setTag(holder);
            } else {
                holder = (RecordHolder) convertView.getTag();
            }
            holder.employeeId.setText(dInfo.getEmp_id());
            holder.employeeName.setText(dInfo.getEmp_name());
            //holder.employeeRate.setText(dInfo.getEmp_rate());
            //holder.Rate.setRating(dInfo.getEmp_rate());
            //holder.image.setImageResource(dInfo.getCategory_image());

            emp_name = dInfo.getEmp_name().toString().trim();


            cat_id = dInfo.getEmp_id().toString().trim();
            Picasso.with(BookAppointmentActivity.this).load(dInfo.getEmp_image()).placeholder(R.mipmap.ic_launcher).into(holder.image);
            //Toast.makeText(BookAppointmentActivity.this, "DATA >> " + dInfo.getCname(), Toast.LENGTH_LONG).show();
            return convertView;

        }
    }



    ////////////////////////////////////////////////////



    private class DoInBackgroundAppointment extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(BookAppointmentActivity.this, "Please Wait...", "Fetching..", false, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getDataUpdate();
            return null;
        }
    }

    public void getDataUpdate() {

        selectedDateStr=selectedDateStr.replaceAll(" ","%20");
        selectedDateStr=selectedDateStr.replaceAll(",","%2C");
        item=item.replaceAll(" ","%20");
        URL="http://"+IP.ip+"/project_para_salon/salon_update_appointment.php?email="+email+"&&app_status=0&&emp_id="+EmpID+"&&emp_name="+emp_name+"&&date="+selectedDateStr+"&&time="+item;
        Log.e("Inserted Elements>>",URL);

        StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSONUpdate(response);
            }


        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookAppointmentActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(BookAppointmentActivity.this);
        requestQueue.add(stringRequest);

    }

    public void showJSONUpdate(String response) {
        try {

            if (response != null) {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

                for (i = 0; i <= result.length(); i++) {
                    String msg = jsonObject.getString(Config.KEY_message);

                }
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
