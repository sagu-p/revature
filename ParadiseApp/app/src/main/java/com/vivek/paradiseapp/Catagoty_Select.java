package com.vivek.paradiseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Catagoty_Select extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String[] array={"Hair Cut","Skin Care","Nail"};
    int img[] = { R.drawable.men_hair, R.drawable.skin2, R.drawable.nail};



    public GridView grd;
    public long bs;

    public String email, s;

    DrawerLayout drawer;
    NavigationView navigationView;

    public void withItems() {
        final String[] items = {"Male", "Female"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Catagoty_Select.this);
        builder.setTitle("Choose One:")
                .setItems(items, new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainHomeActivity.this, items[which] + " is clicked", Toast.LENGTH_SHORT).show();
                        s = items[which].toString();
                    }
                });
        /*builder.setPositiveButton("OK", null);
        builder.setNegativeButton("CANCEL", null);
        builder.setNeutralButton("NEUTRAL", null);*/
        // builder.setPositiveButtonIcon(getResources().getDrawable(android.R.drawable.ic_menu_call, getTheme()));
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        Button button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setBackgroundColor(Color.BLACK);
        button.setPadding(0, 0, 20, 0);
        button.setTextColor(Color.WHITE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagoty__select);


        Intent in = getIntent();
        s = in.getStringExtra("GENDER");


        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        grd = (GridView)findViewById(R.id.grdview);

        DrawableNav();

        CustomAdapter adapter=new CustomAdapter(Catagoty_Select.this, array, img);
        grd.setAdapter(adapter);





        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (array[i]=="Hair Cut")
                {
                    Toast.makeText(Catagoty_Select.this,"A="+array[i],Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Catagoty_Select.this, MainCategoryActivity.class);
                    in.putExtra("GENDER",s);
                    startActivity(in);

                }
                else if (array[i]=="Skin Care")
                {
                    Toast.makeText(Catagoty_Select.this,"A="+array[i],Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Catagoty_Select.this, MainSkinCatagoryActivity.class);
                    in.putExtra("GENDER",s);
                    startActivity(in);

                }
                else if (array[i]=="Nail")
                {
                    Toast.makeText(Catagoty_Select.this,"A="+array[i],Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Catagoty_Select.this, MainNailCatagoryActivity.class);
                    in.putExtra("GENDER",s);
                    startActivity(in);
                }


            }
        });

    }

    public void DrawableNav() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Catagoty_Select.this,NavigationHomeActivity.class));
    }
}
