package com.seneca.android.senfitbeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class exerciseByMuscleGroup extends AppCompatActivity {
    private int id_;
    private DbHelper db;
    private custAdapter cust;
    private ArrayList<Exercise> list = new ArrayList<Exercise>();
    private ListView lv;
    private String[] m_Name;
    private int[] prgmImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_by_muscle_group);

        db = new DbHelper(this);
        id_ = getIntent().getExtras().getInt("mid");
        list = db.getExByMuscles(id_);
        lv= (ListView) findViewById(R.id.listView);

        prgmImages = new int[7]; //changes size when we get more img
        for (int i=0; i <7; i++) {
            String url = "drawable/"+"img"+i;
            prgmImages[i] =  getResources().getIdentifier(url, "drawable", getPackageName());
        }

        cust = new custAdapter(exerciseByMuscleGroup.this,list,prgmImages);
        lv.setAdapter(cust);
    }
}
