package com.vivek.paradiseapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainSkinCatagoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public String gnder;
    public int gender;
    public String URL;
    public GridView list;
    public ProgressDialog loading;
    public int i;
    private Category_main main_module;
    ArrayList<Category_main> Data_list = new ArrayList<>();
    public String cat_id;

    DrawerLayout drawer;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_skin_catagory);

        Intent i = getIntent();
        gnder=i.getStringExtra("GENDER");

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        list = (GridView) findViewById(R.id.grid);

        if(gnder.equals("Male"))
        {
            gender=1;
        }
        else if(gnder.equals("Female"))
        {
            gender=2;
        }

        DrawableNav();

        //http://localhost/project_para_salon/salon_skin_main_category_select_demo.php?gender=1
        URL = "http://"+IP.ip+ "/project_para_salon/salon_skin_main_category_select_demo.php?gender="+gender;
        new MainSkinCatagoryActivity.DoInBackgroundCategory().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv_name = (TextView) view.findViewById(R.id.tvid);
                String CatID = tv_name.getText().toString().trim();

                //  TextView tv_pid = (TextView) view.findViewById(R.id.tvid1);
                // String ID1 = tv_pid.getText().toString().trim();

                //Toast.makeText(MainSkinCatagoryActivity.this, "Category Selected : " + CatID, Toast.LENGTH_LONG).show();
                Intent i1 = new Intent(MainSkinCatagoryActivity.this, SubCatSkinActivity.class);
                i1.putExtra("CatID", CatID);
                i1.putExtra("GENDER",gnder);
                startActivity(i1);
            }
        });

    }

    ////////////////////////////////////////////////

    public void DrawableNav() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

            startActivity(new Intent(getApplicationContext(), CustomerProfile.class));
            // Handle the camera action
        } else if (id == R.id.nav_appointment) {

        } else if (id == R.id.nav_order) {

        } else if (id == R.id.nav_offer) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    ////////////////////////////////////////////////////

    public class DoInBackgroundCategory extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            getData();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(MainSkinCatagoryActivity.this, "Please Wait...", "Fetching..",false,false);

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
                        Toast.makeText(MainSkinCatagoryActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(MainSkinCatagoryActivity.this);
        requestQueue.add(stringRequest);

    }

    public void showJSON(String response) {

        String cid = "";
        String cname = "";
        String cimage = "";
        try {
            Log.e("Error>>","Enter in ShowJson");
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (i = 0; i <= result.length(); i++) {
                JSONObject category_data = result.getJSONObject(i);
                cid = category_data.getString(Config.KEY_Main_cat_id);
                cname = category_data.getString(Config.KEY_Main_cat_name);
                cimage = category_data.getString(Config.KEY_Main_cat_image);

                Log.e("Category_name>>", cname);
                main_module = new Category_main(cid, cname, cimage);
                Data_list.add(main_module);
                //Toast.makeText(getActivity(),"DATA >> "+cimage+"\n"+cname,Toast.LENGTH_LONG).show();
            }
            //Toast.makeText(getActivity(), "DATA >> " + cid + "\n" + cname, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        list.setAdapter(new MainSkinCatagoryActivity.CustomeAdpaterMainCategory(MainSkinCatagoryActivity.this, R.layout.inside_grid_service, Data_list));

    }

    private class CustomeAdpaterMainCategory extends ArrayAdapter<Category_main> {

        private Activity activity;
        private LayoutInflater inflater;
        private ArrayList<Category_main> detailInfo;


        public CustomeAdpaterMainCategory(MainSkinCatagoryActivity mainSkinCatagoryActivity, int inside_grid_service, ArrayList<Category_main> data_list) {

            super(mainSkinCatagoryActivity,inside_grid_service,data_list);

            this.activity = mainSkinCatagoryActivity;
            this.detailInfo = data_list;


        }

        class RecordHolder {
            TextView categoryName;
            TextView categoryId;
            TextView productId;
            ImageView image;

        }

        public View getView(int position, View convertView, ViewGroup parent) {

            Category_main dInfo = this.detailInfo.get(position);
            MainSkinCatagoryActivity.CustomeAdpaterMainCategory.RecordHolder holder = null;
            if (convertView == null) {
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                holder = new MainSkinCatagoryActivity.CustomeAdpaterMainCategory.RecordHolder();
                convertView = inflater.inflate(R.layout.inside_grid_service, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.img_category);
                holder.categoryName = (TextView) convertView.findViewById(R.id.txt_name);
                holder.categoryId = (TextView) convertView.findViewById(R.id.tvid);
                //holder.productId = (TextView) convertView.findViewById(R.id.tvid1);
                convertView.setTag(holder);
            } else {
                holder = (MainSkinCatagoryActivity.CustomeAdpaterMainCategory.RecordHolder) convertView.getTag();
            }
            holder.categoryId.setText(dInfo.getCid());
            holder.categoryName.setText(dInfo.getCname());
            //holder.image.setImageResource(dInfo.getCategory_image());

            cat_id = dInfo.getCid().toString().trim();
            Picasso.with(MainSkinCatagoryActivity.this).load(dInfo.getCimage()).placeholder(R.mipmap.ic_launcher).into(holder.image);

            return convertView;

        }


    }
}
