package com.seneca.android.senfitbeta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dance on 4/4/2017.
 */

public class custAdapter extends BaseAdapter {

    private String name;
    private String description;
    private int id;
    private String license_author;
    private String name_original;
    private String creation_date;
    private String category;
    private int maxw,maxr,numSet;
    private DbHelper db;
    private int imgId;

    Context context;;
    List<Exercise> listEx;
    int cnt;
    private int[] img;


    //constructor to set up our instance variables
    public custAdapter(Context c, List<Exercise> a, int[] img){
        context = c;
        listEx = a;
        this.img = img;
    }


    @Override
    public int getCount() { // return number of elements
        return listEx.size();
    }

    @Override
    public Object getItem(int position) { // return the obj at postion
        return listEx.get(position);
    }

    @Override
    public long getItemId(int position) { //return id of given row
        return 0;
    }

    // take data from array and put it in customlayout.xml
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        row = inflater.inflate(R.layout.customlayout, null);


        Exercise items = (Exercise) getItem(position);

        TextView tv1 = (TextView) row.findViewById(R.id.workout);
        tv1.setText(items.getName());

       /*TextView tv2 = (TextView) row.findViewById(R.id.des);
        tv2.setText(items.getLicense_author());*/
        String n = items.getName();
        String m = items.getDescription();




        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exercise items = (Exercise) getItem(position);
                name = items.getName();
                description = items.getDescription();
                id = items.getId();
                license_author = items.getLicense_author();
                name_original = items.getName_original();
                creation_date = items.getCreation_date();
                category = items.getCategory();
                imgId = img[position];

                Intent intent = new Intent(context,viewtoadd.class);
                Bundle bee = new Bundle();

                bee.putString("_name",name);
                bee.putString("_des",description);
                bee.putInt("_id",id);
                bee.putString("_liA",license_author);
                bee.putString("n_OG",name_original);
                bee.putString("cdate",creation_date);
                bee.putString("cat5",category);
                bee.putInt("imageId",imgId);
                bee.putInt("maxwe",maxw);
                bee.putInt("maxreps",maxr);
                bee.putInt("numofsets",numSet);


                intent.putExtras(bee);
                context.startActivity(intent);
            }
        });



        return row;
    }
}