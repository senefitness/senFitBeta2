package com.seneca.android.senfitbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class musclelist extends AppCompatActivity {

    private ArrayList<Muscle> list = new ArrayList<Muscle>();
    private ArrayList<String> names = new ArrayList<String>();
    private DbHelper db;
    private ListView lv;
    private String[] m_Name;
    private custAdapter2 cust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musclelist);

        int[] prgmImages = {R.drawable.anteriordelt,R.drawable.bicep_brac,R.drawable.bicep_fem,R.drawable.brachialis,R.drawable.gastrocnemius,
                R.drawable.maximus,R.drawable.lati,R.drawable.obliq,R.drawable.pector,R.drawable.quad,R.drawable.rectus,R.drawable.sa,
                R.drawable.soleus,R.drawable.trapezius,R.drawable.tricepsbr};

        db = new DbHelper(this);
        list = db.getMuscles();
        lv= (ListView) findViewById(R.id.mlistView);

        for(int i =0; i < list.size(); i++){
            names.add(list.get(i).getName());
        }
        m_Name = new String[names.size()];
        m_Name = names.toArray(m_Name);

        //ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.select_dialog_item,m_Name);
        cust = new custAdapter2(getApplicationContext(),m_Name,prgmImages);
        lv.setAdapter(cust);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),exerciseByMuscleGroup.class);
                Muscle item = new Muscle();
                item = list.get((int) id);
                intent.putExtra("mid",item.getId());
                startActivity(intent);
            }
        });

    }
}
