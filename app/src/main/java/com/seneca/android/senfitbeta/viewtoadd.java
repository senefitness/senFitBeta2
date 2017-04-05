package com.seneca.android.senfitbeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class viewtoadd extends AppCompatActivity {

    private TextView t1;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtoadd);

        Bundle bud = getIntent().getExtras();
        String s1 = bud.getString("_name");
        String s2 = bud.getString("_des");
        String s3 = bud.getString("_id");
        String s4 = bud.getString("_liA");
        String s5 = bud.getString("n_OG");
        String s6 = bud.getString("cdate");
        String s7 = bud.getString("cat5");
        int pos = bud.getInt("imageId");


        t1 = (TextView)findViewById(R.id.textViewArt);
        imageView = (ImageView) findViewById(R.id.imageView2);

        imageView.setImageResource(pos);
        t1.setText("NAME: "+s1);  //name
        t1.append("\nORIGINAL NAME: "+s5);//original name
        t1.append("\nDESCRIPTION: "+s2); //description
        t1.append("\nID: "+s3); //id
        t1.append("\nAUTHOR: "+s4); //author
        t1.append("\nCREATION DATE: "+s6); //creation date
        t1.append("\nCATEGORY: "+s7); //category


    }
}
