package com.example.user.myhealthcheck;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by user on 19/12/2017.
 */

public class Register extends AppCompatActivity {
    EditText UsernameEt, PasswordEt,NameEt,SurnameEt,AmkaEt,EmailEt,RePassEt,Sosn;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        NameEt = (EditText) findViewById(R.id.input_name);
        SurnameEt = (EditText) findViewById(R.id.input_surname);
        UsernameEt = (EditText) findViewById(R.id.input_username);
        PasswordEt = (EditText) findViewById(R.id.input_password);
        AmkaEt = (EditText) findViewById(R.id.input_amka);
        EmailEt = (EditText) findViewById(R.id.input_email);
        RePassEt = (EditText) findViewById(R.id.input_reEnterPassword);
        Sosn = (EditText) findViewById(R.id.text_sosnu);

    }
    public void OnSignup(View view) {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String name = NameEt.getText().toString();
        String surname = SurnameEt.getText().toString();
        String amka = AmkaEt.getText().toString();
        String email = EmailEt.getText().toString();
        String repass = RePassEt.getText().toString();
        String sosn = Sosn.getText().toString();
        if(sosn.isEmpty()){
             sosn="0";
        }
        if(repass.equals(password)){


            String type = "register";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, username, password ,name,surname,amka,email,sosn);
        }else{
            Toast.makeText(context, "Passwords don't match,please write one password!", Toast.LENGTH_SHORT).show();
        }
    }
    public void OnLogin(View view) {
        Intent menu = new Intent(context, Signin.class);
        startActivity(menu);
    }
}
