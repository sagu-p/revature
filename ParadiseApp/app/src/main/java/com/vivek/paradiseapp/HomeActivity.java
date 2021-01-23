package com.vivek.paradiseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {


    String[] array={"Hair Cut","Skin Care","Nail"};
    int img[] = { R.drawable.men_hair, R.drawable.skin2, R.drawable.nail};

    String[] array2={"Hair Products","Skin Care Products","Nail Products"};
    int img2[] = { R.drawable.hair_product, R.drawable.skin_care_product, R.drawable.nail_care};


    public GridView grd,grd2;
    public long bs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        grd = (GridView)findViewById(R.id.grdview);
        grd2=findViewById(R.id.grdview2);

        CustomAdapter adapter=new CustomAdapter(HomeActivity.this, array, img);
        grd.setAdapter(adapter);

        CustomAdapter adapter2=new CustomAdapter(HomeActivity.this, array2, img2);
        grd2.setAdapter(adapter2);




        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (array[i]=="Hair Cut")
                {
                    Toast.makeText(HomeActivity.this,"A="+array[i],Toast.LENGTH_SHORT).show();
                }
                else if (array[i]=="Skin Care")
                {
                    Toast.makeText(HomeActivity.this,"A="+array[i],Toast.LENGTH_SHORT).show();
                }
                else if (array[i]=="Nail")
                {
                    Toast.makeText(HomeActivity.this,"A="+array[i],Toast.LENGTH_SHORT).show();
                }


            }
        });

        grd2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (array2[i]=="Hair Products")
                {
                    Toast.makeText(HomeActivity.this,"A="+array2[i],Toast.LENGTH_SHORT).show();
                }
                else if (array2[i]=="Skin Care Products")
                {
                    Toast.makeText(HomeActivity.this,"A="+array2[i],Toast.LENGTH_SHORT).show();
                }
                else if (array2[i]=="Nail Products")
                {
                    Toast.makeText(HomeActivity.this,"A="+array2[i],Toast.LENGTH_SHORT).show();
                }


            }
        });



    }

    @Override
    public void onBackPressed() {
        if (bs+2000>System.currentTimeMillis()) {
            //super.onBackPressed();
            //return;
            Intent in = new Intent(Intent.ACTION_MAIN);
            in.addCategory(Intent.CATEGORY_HOME);
            startActivity(in);
            finish();

        }
        else {
            bs=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"Touch again to Exit.",Toast.LENGTH_SHORT).show();
        }
    }
}
