package com.vivek.paradiseapp;

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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public class BrandActivity extends AppCompatActivity {

    private ProgressDialog loading;
    /* Catering_POJO main_module;
     ArrayList<Catering_POJO> Data_list = new ArrayList<Catering_POJO>();*/
    private GridView list;
    ArrayAdapter adapter;
    //String dir_id, sid, d_name, Latitude, Longitude, type, address, contact_no, image;
    String img;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public String URL,s,cat_id,salonid;
    Category_main main_module;
    ArrayList<Category_main> Data_list = new ArrayList<Category_main>();
    public int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);

        list = (GridView)findViewById(R.id.grid);

        pref = getApplicationContext().getSharedPreferences(sec.MyPrefrences, Context.MODE_PRIVATE);
        editor = pref.edit();
        salonid = pref.getString("Email", null);


        URL = "http://"+IP.ip+ "/project_para_salon/salon_brand_select.php";
        new DoInBackgroundCategory().execute();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv_name = (TextView) view.findViewById(R.id.tvid);
                String BrandID = tv_name.getText().toString().trim();

                //  TextView tv_pid = (TextView) view.findViewById(R.id.tvid1);
                // String ID1 = tv_pid.getText().toString().trim();

                //Toast.makeText(BrandActivity.this, "Brand Selected : " + BrandID, Toast.LENGTH_LONG).show();

                Intent i1 = new Intent(BrandActivity.this, ProductActivity.class);
                i1.putExtra("BrandID", BrandID);
                //i1.putExtra("GENDER",s);
                startActivity(i1);
            }
        });

    }

    public class DoInBackgroundCategory extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(BrandActivity.this, "Please Wait...", "Fetching..",false,false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getData();
            return null;
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
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void showJSON(String response) {
        String brandid = "";
        String brandname = "";
        String brandimage = "";
        try {
            Log.e("Error>>","Enter in ShowJson");
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (i = 0; i <= result.length(); i++) {
                JSONObject category_data = result.getJSONObject(i);
                brandid = category_data.getString(Config.KEY_brand_id);
                brandname = category_data.getString(Config.KEY_brand_name);
                brandimage = category_data.getString(Config.KEY_brand_image);

                Log.e("Brand_name>>", brandname);
                main_module = new Category_main(brandid,brandname,brandimage);
                Data_list.add(main_module);
                //Toast.makeText(getActivity(),"DATA >> "+cimage+"\n"+cname,Toast.LENGTH_LONG).show();
            }
            //Toast.makeText(getActivity(), "DATA >> " + cid + "\n" + cname, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        list.setAdapter(new CustomeAdpaterMainCategory(BrandActivity.this, R.layout.inside_grid_service, Data_list));
    }

    public class CustomeAdpaterMainCategory extends ArrayAdapter<Category_main> {
        private Activity activity;
        private LayoutInflater inflater;
        private ArrayList<Category_main> detailInfo;

        public CustomeAdpaterMainCategory(BrandActivity activity, int inside_grid_home, ArrayList<Category_main> data_list) {
            super(activity,inside_grid_home,data_list);

            this.activity = activity;
            this.detailInfo = data_list;
        }
        class RecordHolder {
            TextView brandName;
            TextView brandId;
            ImageView image;

        }

        public View getView(int position, View convertView, ViewGroup parent) {

            Category_main dInfo = this.detailInfo.get(position);
            RecordHolder holder = null;
            if (convertView == null) {
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                holder = new RecordHolder();
                convertView = inflater.inflate(R.layout.inside_grid_service, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.img_category);
                holder.brandName = (TextView) convertView.findViewById(R.id.txt_name);
                holder.brandId = (TextView) convertView.findViewById(R.id.tvid);
                //holder.productId = (TextView) convertView.findViewById(R.id.tvid1);
                convertView.setTag(holder);
            } else {
                holder = (RecordHolder) convertView.getTag();
            }
            holder.brandId.setText(dInfo.getCid());
            holder.brandName.setText(dInfo.getCname());
            //holder.image.setImageResource(dInfo.getCategory_image());

            cat_id = dInfo.getCid().toString().trim();
            Picasso.with(BrandActivity.this).load(dInfo.getCimage()).placeholder(R.mipmap.ic_launcher).into(holder.image);

            return convertView;

        }
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(BrandActivity.this,NavigationHomeActivity.class));
    }
}

