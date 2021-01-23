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

import androidx.appcompat.app.AppCompatActivity;

public class ProductActivity extends AppCompatActivity {

    private ListView list;
    public String URL,cat_id,salonid;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private ProgressDialog loading;
    public int i;
    Category_main main_module;
    ArrayList<Category_main> Data_list = new ArrayList<Category_main>();;
    public String BrandID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent i = getIntent();
        BrandID = i.getStringExtra("BrandID");
//Toast.makeText(SubCatActivity.this, gnder,Toast.LENGTH_LONG).show();

        list = (ListView) findViewById(R.id.list);

        URL = "http://" + IP.ip + "/project_para_salon/salon_product_select.php?brand_id=" + BrandID;
        ///webservices/fringe_product_select.php?brand_id=id2&&salon_id=id2

        new DoInBackgroundCategory().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv_name = (TextView) view.findViewById(R.id.tvid);
                String ProductID = tv_name.getText().toString().trim();
                //Toast.makeText(ProductActivity.this, "Product Selected : " + ProductID, Toast.LENGTH_LONG).show();
                // Toast.makeText(ProductActivity.this, "Gender Selected : " + gender, Toast.LENGTH_SHORT).show();

                Intent i1 = new Intent(ProductActivity.this, Product_itemActivity.class);
                i1.putExtra("ProductID", ProductID);
                i1.putExtra("BrandID", BrandID);
                //i1.putExtra("GENDER", gender);
                startActivity(i1);
            }
        });
    }

    public class DoInBackgroundCategory extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(ProductActivity.this, "Please Wait...", "Fetching..", false, false);
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
                        Toast.makeText(ProductActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(ProductActivity.this);
        requestQueue.add(stringRequest);
    }

    public void showJSON(String response) {
        String productid = "";
        String productname = "";
        String productstock = "";
        String productimage = "";
        try {
            Log.e("Error>>","Enter in ShowJson");
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (i = 0; i <= result.length(); i++) {
                JSONObject brand_data = result.getJSONObject(i);
                productid = brand_data.getString(Config.KEY_product_id);
                productname = brand_data.getString(Config.KEY_product_name);
                productimage = brand_data.getString(Config.KEY_product_image);

                Log.e("Brand_name>>", productname);
                main_module = new Category_main(productid,productname,productimage);
                Data_list.add(main_module);
                //Toast.makeText(getActivity(),"DATA >> "+cimage+"\n"+cname,Toast.LENGTH_LONG).show();
            }
            //Toast.makeText(getActivity(), "DATA >> " + cid + "\n" + cname, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        list.setAdapter(new CustomeAdpaterMainCategory(ProductActivity.this, R.layout.inside_list_home, Data_list));
    }
    public class CustomeAdpaterMainCategory extends ArrayAdapter<Category_main> {
        private Activity activity;
        private LayoutInflater inflater;
        private ArrayList<Category_main> detailInfo;

        public CustomeAdpaterMainCategory(ProductActivity subCatActivity, int inside_list_home, ArrayList<Category_main> data_list) {

            super(subCatActivity, inside_list_home, data_list);


            this.activity = subCatActivity;
            this.detailInfo = data_list;
        }

        class RecordHolder {
            TextView categoryName;
            TextView categoryId;
            ImageView image;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Category_main dInfo = this.detailInfo.get(position);
            RecordHolder holder = null;
            if (convertView == null) {
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                holder = new RecordHolder();
                convertView = inflater.inflate(R.layout.inside_list_home, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.img_category);
                holder.categoryName = (TextView) convertView.findViewById(R.id.txt_name);
                holder.categoryId = (TextView) convertView.findViewById(R.id.tvid);
                convertView.setTag(holder);
            } else {
                holder = (RecordHolder) convertView.getTag();
            }
            holder.categoryId.setText(dInfo.getCid());
            holder.categoryName.setText(dInfo.getCname());
            //holder.image.setImageResource(dInfo.getCategory_image());

            cat_id = dInfo.getCid().toString().trim();
            Picasso.with(ProductActivity.this).load(dInfo.getCimage()).placeholder(R.mipmap.ic_launcher).into(holder.image);
            //Toast.makeText(ProductActivity.this, "DATA >> " + dInfo.getCid(), Toast.LENGTH_LONG).show();
            return convertView;
        }
    }
}
