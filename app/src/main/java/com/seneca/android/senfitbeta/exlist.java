package com.seneca.android.senfitbeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class exlist extends AppCompatActivity {
    private DbHelper db;
    private ArrayList<Exercise> list = new ArrayList<Exercise>();
    private custAdapter cust;
    private  int[] prgmImages;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exlist);

        db = new DbHelper(this);
        list = db.getExercises();
        lv= (ListView) findViewById(R.id.listView);

        prgmImages = new int[7]; //changes size when we get more img
        for (int i=0; i <7; i++) {
            String url = "drawable/"+"img"+i;
            prgmImages[i] =  getResources().getIdentifier(url, "drawable", getPackageName());
        }

        cust = new custAdapter(exlist.this,list,prgmImages);
        lv.setAdapter(cust);
    }
}
