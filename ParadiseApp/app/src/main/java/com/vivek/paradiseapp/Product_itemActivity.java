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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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
import androidx.core.app.NavUtils;

public class Product_itemActivity extends AppCompatActivity {

    public String ProductID,gender;
    private ListView list;
    public String URL,cat_id;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private ProgressDialog loading;
    public int i;
    Product main_module;
    ArrayList<Product> Data_list = new ArrayList<Product>();
    public String BrandID;
    public String id,s,productid = "",productname = "",productcat="",productimage = "",productprice = "",productquantity="",productdescription = "";
    public String SalonID,email;
    public Button btn_service_cart;
    String productname1;
    private Toolbar toolbar;


    /*SharedPreferences pref1;
    SharedPreferences.Editor editor1;
    public  static  final  String MyPrefrences1 = "MyPref1";*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inside_list_sub);
        list=(ListView)findViewById(R.id.list);
        btn_service_cart = (Button) findViewById(R.id.btn_cart);

        pref = getSharedPreferences(sec.MyPrefrences, Context.MODE_PRIVATE);
        pref.edit();

        email = pref.getString("Email", "");

        Intent i = getIntent();
        gender=i.getStringExtra("GENDER");
        ProductID = i.getStringExtra("ProductID");
        BrandID = i.getStringExtra("BrandID");
        //Toast.makeText(Product_itemActivity.this, "Product Selected : " + ProductID, Toast.LENGTH_LONG).show();


        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

        URL = "http://"+IP.ip+ "/project_para_salon/salon_product_description.php?pro_id="+ProductID;
        new DoInBackgroundCategory().execute();

        btn_service_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent in = new Intent(SubCat_itemActivity.this, CartActivity.class);
                in.putExtra("SubcatID", SubcatID);
                in.putExtra("SalonID", SalonID);
                startActivity(in);*/
                //new DoInBackgroundCategoryInsert().execute();

                startActivity(new Intent(Product_itemActivity.this,BrandActivity.class));

            }
        });

    }

    public class DoInBackgroundCategory extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute() {
            loading = ProgressDialog.show(Product_itemActivity.this, "Please Wait...", "Fetching..", false, false);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Toast.makeText(Product_itemActivity.this, "Product Selected : " + productname, Toast.LENGTH_LONG).show();
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
                        Toast.makeText(Product_itemActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(Product_itemActivity.this);
        requestQueue.add(stringRequest);
    }

    public void showJSON(String response) {
        try {
            Log.e("Error>>","Enter in ShowJson");
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (i = 0; i <= result.length(); i++) {
                JSONObject category_data = result.getJSONObject(i);
                productid = category_data.getString(Config.KEY_product_id);
                productname = category_data.getString(Config.KEY_product_name);
                productcat = category_data.getString(Config.KEY_product_cat);
                productimage = category_data.getString(Config.KEY_product_image);
                productprice = category_data.getString(Config.KEY_product_price);
                productdescription = category_data.getString(Config.KEY_product_description);
                productquantity = category_data.getString(Config.KEY_product_quantity);

                Log.e("Product_name>>", productname);
                main_module = new Product(productid,productname,productcat,productimage,productprice,productdescription,productquantity);
                Data_list.add(main_module);
                //Toast.makeText(SubCatActivity.this,"DATA >> "+cimage+"\n"+cname,Toast.LENGTH_LONG).show();
            }
            //Toast.makeText(SubCatActivity.this, "DATA >> " + cid + "\n" + cname, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        list.setAdapter(new CustomeAdpaterMainCategory(Product_itemActivity.this, R.layout.activity_product_item, Data_list));
    }

    private class CustomeAdpaterMainCategory extends ArrayAdapter<Product> {
        private Activity activity;
        private LayoutInflater inflater;
        private ArrayList<Product> detailInfo;

        public CustomeAdpaterMainCategory(Product_itemActivity product_itemActivity, int inside_list_sub, ArrayList<Product> data_list) {
            super(product_itemActivity,inside_list_sub,data_list);

            this.activity=product_itemActivity;
            this.detailInfo = data_list;
        }
        class RecordHolder {
            TextView productQuantity;
            TextView productName;
            TextView productId;
            TextView productPrice;
            TextView productDescription;
            ImageView image;

        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Product dInfo = this.detailInfo.get(position);
            RecordHolder holder = null;
            if (convertView == null) {
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                holder = new RecordHolder();
                convertView = inflater.inflate(R.layout.activity_product_item, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.image1);
                holder.productName = (TextView) convertView.findViewById(R.id.list_item1);
                holder.productId = (TextView) convertView.findViewById(R.id.tvid1);
                holder.productPrice = (TextView) convertView.findViewById(R.id.list_item2);
                holder.productQuantity = (TextView) convertView.findViewById(R.id.list_item3);
                holder.productDescription = (TextView) convertView.findViewById(R.id.list_item4);
                convertView.setTag(holder);
            } else {
                holder = (RecordHolder) convertView.getTag();
            }
            holder.productId.setText(dInfo.getPid());
            holder.productName.setText(dInfo.getPname());
            holder.productDescription.setText(dInfo.getPdescription());
            holder.productQuantity.setText(dInfo.getPquantity());
            holder.productPrice.setText(dInfo.getPprice());
            // holder.image.setImageResource(dInfo.getCimage());
            productname1= dInfo.getPname();

            // cat_id = dInfo.getCid().toString().trim();
            Picasso.with(Product_itemActivity.this).load(dInfo.getPimage()).placeholder(R.mipmap.ic_launcher).into(holder.image);
            /*Toast.makeText(Product_itemActivity.this, "DATA >> " + dInfo.getPid(), Toast.LENGTH_SHORT).show();
            Toast.makeText(Product_itemActivity.this, "Name >> " + dInfo.getPname(), Toast.LENGTH_LONG).show();*/
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
            loading = ProgressDialog.show(Product_itemActivity.this, "Please Wait...", "Fetching..", false, false);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);



          /*  pref1 = getApplicationContext().getSharedPreferences(MyPrefrences1, Context.MODE_PRIVATE);
            editor1 =pref1.edit();
            editor1.putString("ProductID", ProductID);
            editor1.commit();*/

            Intent in = new Intent(Product_itemActivity.this, CartActivity.class);
            in.putExtra("SalonID", SalonID);
            in.putExtra("ProductID", ProductID);
            startActivity(in);
        }
    }

    public void getDataInsert() {

        productname1=productname1.replaceAll(" ","%20");
        productname1=productname1.replaceAll("'","%27");
        URL = "http://"+IP.ip+ "/webservices/fringe_product_cart_insert.php?salon_id="+SalonID+"&&email="+email+"&&brand_id="+BrandID+"&&pro_id="+ProductID+"&&pro_name="+productname1+"&&pro_price="+productprice+"&&pro_image="+productimage;
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
                        Toast.makeText(Product_itemActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(Product_itemActivity.this);
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
