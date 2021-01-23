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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private String SubcatID,SalonID,ProductID;
    private ListView listService,listProduct;
    public String URL,cat_id,s;
    SharedPreferences pref, pref1;
    SharedPreferences.Editor editor, edittor1;
    private ProgressDialog loading;
    String subcatid = "";
    String subcatname = "";
    String subcatimage = "";
    String productid = "";
    String productname = "";
    String productimage = "";
    public int i;
    Service_cart main_moduleService;
    ArrayList<Service_cart> Data_listService = new ArrayList<Service_cart>();;
    //Product_cart main_moduleProduct;
    //ArrayList<Product_cart> Data_listProduct = new ArrayList<Product_cart>();;
    String subcatprice = "";
    String productprice = "";
    Button btn_cart;
    public String email;
    public Button btn_checkout;
    public String SubcatName;
    private String serviceCartid,productCartid;
    private BottomNavigationView bottom_navigate;
    public String gender;
    public String URL1;
    public String gender1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listService=(ListView)findViewById(R.id.service_list);
        //listProduct=(ListView)findViewById(R.id.product_list);
        btn_cart = (Button) findViewById(R.id.btn_cart);
        btn_checkout = (Button) findViewById(R.id.btn_checkout);
        //ImageButton btn_delete = (ImageButton) findViewById(R.id.delete);

        pref = getSharedPreferences(sec.MyPrefrences, Context.MODE_PRIVATE);
        pref.edit();
        email = pref.getString("Email", null);

        //Toast.makeText(getApplicationContext(),email,Toast.LENGTH_SHORT).show();
        /*pref1 = getSharedPreferences(Product_itemActivity.MyPrefrences1, Context.MODE_PRIVATE);
        pref1.edit();*/

        // ProductID = pref1.getString("ProductID", null);

        Intent i = getIntent();
        gender=i.getStringExtra("GENDER");
        SubcatID=i.getStringExtra("SubcatID");
        SubcatName=i.getStringExtra("SubcatName");
        ProductID=i.getStringExtra("ProductID");
        //Toast.makeText(CartActivity.this, "SubCat Selected : " + gender, Toast.LENGTH_LONG).show();
        //Toast.makeText(CartActivity.this, "Products Selected : " + ProductID, Toast.LENGTH_LONG).show();

        //URL = "http://"+IP.ip+ "/webservices/fringe_cart_select.php?sub_cat_id="+SubcatID;
        new DoInBackgroundCategoryService().execute();
        //new DoInBackgroundCategoryProduct().execute();


        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*
                if(gender.equals("1"))
                {
                    gender1="Male";
                }
                else if(gender.equals("2"))
                {
                    gender1="Female";
                }
*/

                //Toast.makeText(getApplicationContext(),gender1,Toast.LENGTH_SHORT).show();

                Intent in = new Intent(CartActivity.this, NavigationHomeActivity.class);
                //in.putExtra("GENDER",gender1);
                startActivity(in);

            }
        });
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email == null)
                {
                    Intent in = new Intent(CartActivity.this, sec.class);
                    startActivity(in);
                }
                else {
                   // Toast.makeText(CartActivity.this, "Selected : " + SubcatID, Toast.LENGTH_LONG).show();
                    Intent in = new Intent(CartActivity.this, BookAppointmentActivity.class);
                    //in.putExtra("SalonID", SalonID);
                    in.putExtra("SubcatID", SubcatID);
                    in.putExtra("SubcatName", subcatname);
                    startActivity(in);
                }



            }
        });

    }



    public class DoInBackgroundCategoryService extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(CartActivity.this, "Please Wait...", "Fetching..", false, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getDataService();
            //getDataProduct();
            return null;
        }
    }

    public void getDataService() {
        URL = "http://"+IP.ip+ "/project_para_salon/salon_service_cart_select.php?email="+email;
        Log.e("Inserted Elements>>",URL);

        StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSONService(response);
            }


        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CartActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(CartActivity.this);
        requestQueue.add(stringRequest);
    }

    public void showJSONService(String response) {

        try {
            Log.e("Error>>","Enter in ShowJson");
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (i = 0; i <= result.length(); i++) {
                JSONObject category_data = result.getJSONObject(i);
                serviceCartid = category_data.getString(Config.KEY_servicecart_id);
                subcatid = category_data.getString(Config.KEY_sub_cat_id);
                subcatname = category_data.getString(Config.KEY_sub_cat_name);
                subcatprice = category_data.getString(Config.KEY_sub_cat_price);
                subcatimage = category_data.getString(Config.KEY_sub_cat_image);

                Log.e("SubCategory_name>>", subcatname);
                main_moduleService = new Service_cart(serviceCartid,subcatid, subcatname,subcatprice, subcatimage);
                Data_listService.add(main_moduleService);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listService.setAdapter(new CustomeAdpaterMainCategoryService(CartActivity.this, R.layout.activity_cart, Data_listService));
    }
    public class CustomeAdpaterMainCategoryService extends ArrayAdapter<Service_cart> {
        private Activity activity;
        private LayoutInflater inflater;
        private ArrayList<Service_cart> detailInfo;
        private String ServiceCart;

        public CustomeAdpaterMainCategoryService(CartActivity cartActivity, int inside_list_home, ArrayList<Service_cart> data_list) {

            super(cartActivity, inside_list_home, data_list);


            this.activity = cartActivity;
            this.detailInfo = data_list;
        }

        class RecordHolder {
            TextView categoryPrice;
            TextView categoryName;
            TextView categoryId,ServicecartId;
            ImageView image;
            ImageButton deleteservice;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final Service_cart dInfo = this.detailInfo.get(position);
            RecordHolder holder = null;
            if (convertView == null) {
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                holder = new RecordHolder();
                convertView = inflater.inflate(R.layout.inside_list_cart, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.img_category1);
                holder.categoryName = (TextView) convertView.findViewById(R.id.txt_name1);
                holder.categoryPrice = (TextView) convertView.findViewById(R.id.txt_name2);
                holder.categoryId = (TextView) convertView.findViewById(R.id.tvid);
                holder.ServicecartId = (TextView) convertView.findViewById(R.id.tvid1);

                holder.deleteservice = (ImageButton)convertView.findViewById(R.id.delete);
                //holder.productId = (TextView) convertView.findViewById(R.id.tvid1);
                convertView.setTag(holder);
            } else {
                holder = (RecordHolder) convertView.getTag();
            }
            holder.ServicecartId.setText(dInfo.getCid());
            holder.categoryId.setText(dInfo.getCsubcatid());
            holder.categoryName.setText(dInfo.getCname());
            holder.categoryPrice.setText(dInfo.getCprice());
            //holder.image.setImageResource(dInfo.getCategory_image());

            Picasso.with(CartActivity.this).load(dInfo.getCimage()).placeholder(R.mipmap.ic_launcher).into(holder.image);
          //Picasso.with(CartActivity.this).load(dInfo.getCimage()).placeholder(R.mipmap.ic_launcher).into(holder.image);



            final RecordHolder finalHolder = holder;
            finalHolder.deleteservice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ServiceCart=dInfo.getCid();
                    URL1="http://"+IP.ip+"/project_para_salon/salon_service_cart_delete.php?servicecart_id="+ServiceCart;
                    new DoInBackgroundCategoryServiceDelete().execute();

                }
            });


            cat_id = dInfo.getCid().toString().trim();
            //Toast.makeText(CartActivity.this, "DATA >> " + dInfo.getCid(), Toast.LENGTH_LONG).show();
            return convertView;

        }
    }


    //////////////////////////////////////////////////

    public class DoInBackgroundCategoryServiceDelete extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            getDataServiceDelete();
            Intent in = new Intent(CartActivity.this, CartActivity.class);
            //in.putExtra("SalonID", SalonID);
            startActivity(in);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(CartActivity.this, "Please Wait...", "Fetching..", false, false);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            /*Intent in = new Intent(CartActivity.this, CartActivity.class);
            in.putExtra("SalonID", SalonID);
            startActivity(in);*/
            /*in.putExtra("SubcatID", SubcatID);
            in.putExtra("SubcatName", subcatname);
            in.putExtra("SalonID", SalonID);
            in.putExtra("CatID", CatID);*/

        }
    }

    public void getDataServiceDelete() {

        Log.e("Inserted Elements>>",URL1);

        StringRequest stringRequest = new StringRequest(URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSONServiceDelete(response);
            }


        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CartActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(CartActivity.this);
        requestQueue.add(stringRequest);

    }

    public void showJSONServiceDelete(String response) {

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


    /////////////////////////////////////////////////////

}
