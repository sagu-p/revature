package com.vivek.paradiseapp;

import androidx.appcompat.app.AppCompatActivity;

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

public class SubCat_itemActivity extends AppCompatActivity {

    public String SubcatID,gender,SalonID;
    private ListView list;
    public String URL,cat_id,CatID,email;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private ProgressDialog loading;
    public int i;
    Button btn_service_cart;
    Category_sub main_module;
    ArrayList<Category_sub> Data_list = new ArrayList<Category_sub>();;
    public String gnder;
    public String id,s,subcatid = "",subcatname = "",subcatimage = "",subcatprice = "",subcatdescription = "";
    public String subcatname1;
    public String s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inside_list_sub);


        list=(ListView)findViewById(R.id.list);
        btn_service_cart = (Button) findViewById(R.id.btn_cart);

        pref = getSharedPreferences(sec.MyPrefrences, Context.MODE_PRIVATE);
        pref.edit();

        email = pref.getString("Email", null);

        Intent i = getIntent();
        gender=i.getStringExtra("GENDER");
        SubcatID=i.getStringExtra("SubcatID");
        CatID=i.getStringExtra("CatID");
        //Toast.makeText(SubCat_itemActivity.this, "SubCat Selected : " + SubcatID, Toast.LENGTH_LONG).show();
        //Toast.makeText(SubCat_itemActivity.this, "Gender Selected_sub_item : " + gender, Toast.LENGTH_SHORT).show();

        URL = "http://"+IP.ip+ "/project_para_salon/salon_sub_category_description.php?sub_cat_id="+SubcatID;
        new DoInBackgroundCategory().execute();

        btn_service_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*                Intent in = new Intent(SubCat_itemActivity.this, CartActivity.class);
                in.putExtra("SubcatID", SubcatID);
                //in.putExtra("SalonID", SalonID);
                startActivity(in);*/
                new DoInBackgroundCategoryInsert().execute();

            }
        });

    }

    public class DoInBackgroundCategory extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            loading = ProgressDialog.show(SubCat_itemActivity.this, "Please Wait...", "Fetching..", false, false);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Toast.makeText(SubCat_itemActivity.this, "SubCat Selected : " + SubcatID, Toast.LENGTH_LONG).show();
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
                        Toast.makeText(SubCat_itemActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(SubCat_itemActivity.this);
        requestQueue.add(stringRequest);
    }

    public void showJSON(String response) {

        try {
            Log.e("Error>>","Enter in ShowJson");
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (i = 0; i <= result.length(); i++) {
                JSONObject category_data = result.getJSONObject(i);
                subcatid = category_data.getString(Config.KEY_sub_cat_id);
                subcatname = category_data.getString(Config.KEY_sub_cat_name);
                subcatimage = category_data.getString(Config.KEY_sub_cat_image);
                subcatprice = category_data.getString(Config.KEY_sub_cat_price);
                subcatdescription = category_data.getString(Config.KEY_sub_cat_description);

                Log.e("SubCategory_name>>", subcatname);
                main_module = new Category_sub(subcatid,subcatname,subcatimage,subcatprice,subcatdescription);
                Data_list.add(main_module);
                //Toast.makeText(SubCatActivity.this,"DATA >> "+cimage+"\n"+cname,Toast.LENGTH_LONG).show();
            }
            //Toast.makeText(SubCatActivity.this, "DATA >> " + cid + "\n" + cname, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }



        list.setAdapter(new CustomeAdpaterMainCategory(SubCat_itemActivity.this, R.layout.activity_sub_cat_item, Data_list));
    }

    public class CustomeAdpaterMainCategory extends ArrayAdapter<Category_sub> {
        private Activity activity;
        private LayoutInflater inflater;
        private ArrayList<Category_sub> detailInfo;

        public CustomeAdpaterMainCategory(SubCat_itemActivity subCatItemActivity, int inside_list_sub, ArrayList<Category_sub> data_list) {

            super(subCatItemActivity, inside_list_sub, data_list);


            this.activity = subCatItemActivity;
            this.detailInfo = data_list;
        }

        class RecordHolder {
            TextView categoryName;
            TextView categoryId;
            TextView categoryPrice;
            TextView categoryDescription;
            ImageView image;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Category_sub dInfo = this.detailInfo.get(position);
            RecordHolder holder = null;
            if (convertView == null) {
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                holder = new RecordHolder();
                convertView = inflater.inflate(R.layout.activity_sub_cat_item, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.image1);
                holder.categoryName = (TextView) convertView.findViewById(R.id.list_item1);
                holder.categoryId = (TextView) convertView.findViewById(R.id.tvid1);
                holder.categoryPrice = (TextView) convertView.findViewById(R.id.list_item2);
                holder.categoryDescription = (TextView) convertView.findViewById(R.id.list_item3);
                convertView.setTag(holder);
            } else {
                holder = (RecordHolder) convertView.getTag();
            }
            holder.categoryId.setText(dInfo.getCid());
            holder.categoryName.setText(dInfo.getCname());
            holder.categoryDescription.setText(dInfo.getCdescription());
            holder.categoryPrice.setText(dInfo.getCprice());
            // holder.image.setImageResource(dInfo.getCimage());

            // cat_id = dInfo.getCid().toString().trim();
            s1 = dInfo.getCimage().toString();
            Picasso.with(SubCat_itemActivity.this).load(dInfo.getCimage()).placeholder(R.mipmap.ic_launcher).into(holder.image);
            //Toast.makeText(SubCat_itemActivity.this, "DATA >> " + dInfo.getCid(), Toast.LENGTH_LONG).show();
            return convertView;

        }
    }



    public class DoInBackgroundCategoryInsert extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            getDataInsert();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(SubCat_itemActivity.this, "Please Wait...", "Fetching..", false, false);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //Toast.makeText(SubCat_itemActivity.this, "Gender Selected : " + gender, Toast.LENGTH_SHORT).show();


            //String str_gender = Integer.toString(gender);

            Intent in = new Intent(SubCat_itemActivity.this, CartActivity.class);
            in.putExtra("SubcatID", SubcatID);
            in.putExtra("SubcatName", subcatname);
            in.putExtra("GENDER", gender);
            in.putExtra("CatID", CatID);

            startActivity(in);
        }
    }

    public void getDataInsert() {

        //insert url=/webservices/fringe_cart_insert.php?email=&&cat_id=&&sub_cat_id=&&sub_cat_name=&&sub_cat_price=&&sub_cat_image=
        Log.e("img: ", s1);
        subcatname1=subcatname.replaceAll(" ","%20");
        URL = "http://"+IP.ip+ "/project_para_salon/salon_servicecart_insert_demo.php?email="+email+"&&sub_cat_id="+SubcatID+"&&sub_cat_name="+subcatname1+"&&sub_cat_price="+subcatprice+"&&sub_cat_image="+subcatimage;
        Log.e("Inserted Elements>>",URL);

        StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSONInsert(response);
            }


        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SubCat_itemActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(SubCat_itemActivity.this);
        requestQueue.add(stringRequest);

    }

    public void showJSONInsert(String response) {

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

}
