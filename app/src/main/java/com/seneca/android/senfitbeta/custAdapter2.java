package com.seneca.android.senfitbeta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.seneca.android.senfitbeta.Muscle;
import com.seneca.android.senfitbeta.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dance on 4/5/2017.
 */

public class custAdapter2 extends BaseAdapter {

    private Context context;;
    private String[] listEx;
    private int[] img;

    public custAdapter2(Context c, String[] a, int[] img){
        context = c;
        listEx = a;
        this.img = img;
    }

    @Override
    public int getCount() { // return number of elements
        return listEx.length;
    }

    @Override
    public Object getItem(int position) { // return the obj at postion
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        row = inflater.inflate(R.layout.layout_list_row, null);



        TextView tv1 = (TextView) row.findViewById(R.id.words);
        tv1.setText(listEx[position]);

        ImageView imageView = (ImageView) row.findViewById(R.id.pics);
        imageView.setImageResource(img[position]);




        return row;
    }
}
