package com.seneca.android.senfitbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private Button log,register;
    private EditText etEmail,etPassword;
    private DbHelper db;
    private Session sess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        log =(Button)findViewById(R.id.login);
        register = (Button)findViewById(R.id.signup);
        etEmail =(EditText)findViewById(R.id.email);
        etPassword = (EditText)findViewById(R.id.password);

        db = new DbHelper(this);
        sess = new Session(this);


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,SignUp.class));
            }
        });

        if(sess.loggedin()){
            startActivity(new Intent(Login.this,MainActivity.class));
        }

    }

    public void login(){
        String email = etEmail.getText().toString();
        String pass = etPassword.getText().toString();

        if(db.getUser(email,pass)){
            sess.setLoggedin(true,email,pass);
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(),"Wrong email/passwrod",Toast.LENGTH_SHORT).show();
        }
    }
}