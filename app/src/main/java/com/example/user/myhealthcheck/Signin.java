package com.example.user.myhealthcheck;

/**
 * Created by user on 19/12/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Signin extends ActionBarActivity {

    EditText UsernameEt, PasswordEt;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_user);
        UsernameEt = (EditText)findViewById(R.id.input_email);
        PasswordEt = (EditText)findViewById(R.id.input_password);
    }

    public void OnLogin(View view) {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }

    public void OnRegister(View view) {
        Intent myIntent = new Intent(Signin.this, Register.class);
        startActivity(myIntent);
      // context.startActivity(new Intent(context, Register.class));
    }
}
