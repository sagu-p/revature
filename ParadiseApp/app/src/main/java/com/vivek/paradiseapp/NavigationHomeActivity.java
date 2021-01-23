package com.vivek.paradiseapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class NavigationHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;

    String[] array={"Hair Cut","Skin Care","Nail"};
    int img[] = { R.drawable.men_hair, R.drawable.skin2, R.drawable.nail};

    String[] array2={"Hair Products","Skin Care Products","Nail Products"};
    int img2[] = { R.drawable.hair_product, R.drawable.skin_care_product, R.drawable.nail_care};


    public GridView grd,grd2;
    public long bs;

    DrawerLayout drawer;
    NavigationView navigationView;

    public String s;
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    public String email;

    public void withItems() {
        final String[] items = {"Male", "Female"};

        AlertDialog.Builder builder = new AlertDialog.Builder(NavigationHomeActivity.this);
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
        setContentView(R.layout.activity_navigation_home);

        pref = getApplicationContext().getSharedPreferences(sec.MyPrefrences, Context.MODE_PRIVATE);
        editor = pref.edit();
        email = pref.getString("Email", null);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        DrawableNav();

        withItems();

        grd = (GridView)findViewById(R.id.grdview);
        grd2=findViewById(R.id.grdview2);

        CustomAdapter adapter=new CustomAdapter(NavigationHomeActivity.this, array, img);
        grd.setAdapter(adapter);

        CustomAdapter adapter2=new CustomAdapter(NavigationHomeActivity.this, array2, img2);
        grd2.setAdapter(adapter2);




        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (array[i]=="Hair Cut")
                {
                    Toast.makeText(NavigationHomeActivity.this,array[i],Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(NavigationHomeActivity.this, MainCategoryActivity.class);
                    in.putExtra("GENDER",s);
                    startActivity(in);

                }
                else if (array[i]=="Skin Care")
                {
                    Toast.makeText(NavigationHomeActivity.this,array[i],Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(NavigationHomeActivity.this, MainSkinCatagoryActivity.class);
                    in.putExtra("GENDER",s);
                    startActivity(in);

                }
                else if (array[i]=="Nail")
                {
                    Toast.makeText(NavigationHomeActivity.this,array[i],Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(NavigationHomeActivity.this, MainNailCatagoryActivity.class);
                    in.putExtra("GENDER",s);
                    startActivity(in);
                }


            }
        });

        grd2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (array2[i]=="Hair Products")
                {
                    Toast.makeText(NavigationHomeActivity.this,array2[i],Toast.LENGTH_SHORT).show();
                    Intent i1 = new Intent(NavigationHomeActivity.this, BrandActivity.class);
                    //i1.putExtra("BrandID", BrandID);
                    //i1.putExtra("GENDER",s);
                    startActivity(i1);
                }
                else if (array2[i]=="Skin Care Products")
                {
                    Toast.makeText(NavigationHomeActivity.this,array2[i],Toast.LENGTH_SHORT).show();
                }
                else if (array2[i]=="Nail Products")
                {
                    Toast.makeText(NavigationHomeActivity.this,array2[i],Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

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
            startActivity(new Intent(getApplicationContext(), Appointment.class));

        } else if (id == R.id.nav_order) {

            Intent i = new Intent(NavigationHomeActivity.this,CartActivity.class);
            i.putExtra("GENDER",s);
            startActivity(i);

        } else if (id == R.id.nav_offer) {


            startActivity(new Intent(NavigationHomeActivity.this, temp.class));

        }
        else if (id == R.id.btn_logout){
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.commit();
            startActivity(new Intent(getApplicationContext(),LandingActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
