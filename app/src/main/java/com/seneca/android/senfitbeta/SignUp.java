package com.seneca.android.senfitbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    Button r;
    TextView x,Word;
    private EditText etEmail,etPass;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = new DbHelper(this);

        r =(Button)findViewById(R.id.Reg_);
        x =(TextView)findViewById(R.id.Log_);
        etEmail=(EditText)findViewById(R.id.Email);
        etPass =(EditText)findViewById(R.id.password);
        Word =(TextView)findViewById(R.id.words_);

        String info ="please input a valid email address";

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, MainActivity.class));
            }
        });
    }

    //reg
    public void register(){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);

        if(!m.matches()){
            Toast.makeText(getApplicationContext(),"Wroung Email Format",Toast.LENGTH_LONG).show();
        }
        else if(email.isEmpty() && pass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Fields can not be empty ",Toast.LENGTH_LONG).show();
        }
        else if(db.checkEmailExists(email)){
            Toast.makeText(getApplicationContext(),email+" Already Exist",Toast.LENGTH_LONG).show();
        }

        else{
            db.addUsers(email,pass);
            Toast.makeText(getApplicationContext(),"Logged in ",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignUp.this, MainActivity.class));
        }
    }
}