package com.vivek.paradiseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import javax.xml.transform.Templates;

class CustomAdapter extends BaseAdapter {

    int image[];
    String txt[];
    Context mainActivity;
    LayoutInflater inflater;

    public CustomAdapter(HomeActivity homeActivity, String[] array, int[] img) {

        this.mainActivity = homeActivity;
        this.image = img;
        this.txt = array;

    }

    public CustomAdapter(NavigationHomeActivity navigationHomeActivity, String[] array, int[] img) {
        this.mainActivity = navigationHomeActivity;
        this.image = img;
        this.txt = array;

    }

    public CustomAdapter(Catagoty_Select catagoty_select, String[] array, int[] img) {
        this.mainActivity = catagoty_select;
        this.image = img;
        this.txt = array;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        inflater = (LayoutInflater) mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.grid_item, null);

        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        TextView tv = (TextView) view.findViewById(R.id.textView);

        iv.setImageResource(image[i]);
        tv.setText(txt[i]);

        return view;
    }
}
